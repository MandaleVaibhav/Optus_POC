package com.example.optuspoc.view

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.optuspoc.model.ModelPhoto
import com.example.optuspoc.model.ModelUserMaster
import com.example.optuspoc.networkLayer.ApiInterface
import com.example.optuspoc.repository.RepositoryViewModel
import com.example.optuspoc.viewModel.ViewModelAlbumInformation
import com.example.optuspoc.viewModel.ViewModelUserInformation
import io.reactivex.Maybe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import java.net.SocketException
class AlbumInformationFragmentTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var mApiInterface: ApiInterface

    lateinit var mViewModelAlbumInformation: ViewModelAlbumInformation

    lateinit var mRepositoryViewModel: RepositoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mRepositoryViewModel = RepositoryViewModel()
        val application = Mockito.mock(Application::class.java)
        this.mViewModelAlbumInformation = ViewModelAlbumInformation(application)
    }
    @Test
    fun test_getAlbumInformationAPISuccess() {
        Mockito.`when`(this.mApiInterface.getPhotosList("1")).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<RepositoryViewModel>())
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<ArrayList<ModelPhoto>>
        this.mViewModelAlbumInformation.mPhotosList.observeForever(observer)

        this.mViewModelAlbumInformation.getPhotosList("1")
        Thread.sleep(10000)
        assertNotNull(this.mViewModelAlbumInformation.mPhotosList.value)
    }

    /**
     * This test should be fail because we will get success response from API
     * */
    @Test
    fun test_getAlbumInformationError() {
        Mockito.`when`(this.mApiInterface.getPhotosList("1")).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        val observer = Mockito.mock(Observer::class.java) as Observer<ArrayList<ModelPhoto>>
        this.mViewModelAlbumInformation.mPhotosList.observeForever(observer)

        this.mViewModelAlbumInformation.getPhotosList("1")
        Thread.sleep(10000)
        assertNotNull(this.mViewModelAlbumInformation.mPhotosList.value)

    }

}