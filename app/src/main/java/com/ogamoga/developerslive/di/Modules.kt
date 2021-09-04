package com.ogamoga.developerslive.di

import android.content.Context
import androidx.room.Room
import com.ogamoga.developerslive.data.database.AppDatabase
import com.ogamoga.developerslive.data.database.dao.Dao
import com.ogamoga.developerslive.data.repository.LocalRepository
import com.ogamoga.developerslive.data.repository.RemoteRepository
import com.ogamoga.developerslive.domain.usecase.UseCase
import com.ogamoga.developerslive.screens.item.ItemViewModel
import com.ogamoga.developerslive.data.api.ApiFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module(createdAtStart = true) {
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "user_database")
            .build()
    }

    fun provideCardDao(database: AppDatabase): Dao {
        return database.dao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCardDao(get()) }
}

val networkModule = module {
    single { ApiFactory.apiService }
}

val repositoryModule = module {
    single { LocalRepository(get()) }
    single { RemoteRepository(get()) }
}

val screenModule = module {
    single { UseCase(get(), get()) }
    viewModel { ItemViewModel(get()) }
}