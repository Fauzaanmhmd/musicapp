package com.example.musicapp.di

import android.app.Application
import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicapp.data.MusicRepository
import com.example.musicapp.data.remote.MusicApi
import com.example.musicapp.domain.usecase.NextSongUseCase
import com.example.musicapp.domain.usecase.PlaySongUseCase
import com.example.musicapp.domain.usecase.PreviousSongUseCase
import com.example.musicapp.domain.usecase.SearchSongUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.deezer.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMusicApi(retrofit: Retrofit): MusicApi {
        return retrofit.create(MusicApi::class.java)
    }

    @Provides
    fun provideSearchSongUseCase(repository: MusicRepository): SearchSongUseCase {
        return SearchSongUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    @Provides
    fun providePlaySongUseCase(exoPlayer: ExoPlayer): PlaySongUseCase {
        return PlaySongUseCase(exoPlayer)
    }

    @Provides
    fun provideNextSongUseCase(): NextSongUseCase {
        return NextSongUseCase()
    }

    @Provides
    fun providePreviousSongUseCase(): PreviousSongUseCase {
        return PreviousSongUseCase()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}

