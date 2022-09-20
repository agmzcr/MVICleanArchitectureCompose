package dev.agmzcr.mvicleanusersapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.agmzcr.mvicleanusersapp.data.remote.UsersApi
import dev.agmzcr.mvicleanusersapp.data.repository.UserRepositoryImpl
import dev.agmzcr.mvicleanusersapp.domain.model.User
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

class UserRepositoryTest {

    private val user = User("Armando", "Lopez", "Barcelona", "https://randomuser.me/api/portraits/thumb/men/75.jpg")

    private val myDispatcher: Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/?inc=name" -> MockResponse().apply { addResponse("api_name.json") }
                "/?inc=location" -> MockResponse().apply { addResponse("api_location.json") }
                "/?inc=picture" -> MockResponse().apply { addResponse("api_picture.json") }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }

    fun MockResponse.addResponse(filePath: String): MockResponse {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val source = inputStream?.source()?.buffer()
        source?.let {
            setResponseCode(200)
            setBody(it.readString(StandardCharsets.UTF_8))
        }
        return this
    }

    private val mockWebServer = MockWebServer().apply {
        url("/")
        dispatcher = myDispatcher
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UsersApi::class.java)

    private val dao = UserDaoTest()

    private val repository = UserRepositoryImpl(retrofit, dao)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Users on the DB are retrieved correctly`() {
        val users = repository.getAllUsers()
        assertEquals(2, users.value?.size)
    }

    @Test
    fun `Users is deleted correctly`() {
        runBlocking {
            repository.deleteUser(user)

            val users = repository.getAllUsers()
            assertEquals(1, users.value?.size)
        }
    }

    @Test
    fun `Users is fetched correctly`() {
        runBlocking {
            val newUser = repository.addUser()

            val users = repository.getAllUsers()
            assertEquals(3, users.value?.size)
            assertEquals(newUser.name, "Jennie")
            assertEquals(newUser.lastName, "Nichols")
            assertEquals(newUser.city, "Billings")
            assert(newUser.thumbnail!!.contains("thumb/men/75.jpg"))
        }
    }
}
