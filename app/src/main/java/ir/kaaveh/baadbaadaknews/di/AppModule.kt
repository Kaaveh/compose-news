package ir.kaaveh.baadbaadaknews.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.kaaveh.baadbaadaknews.common.Constants.Companion.BASE_URL
import ir.kaaveh.baadbaadaknews.data.FavoriteNewsDatabase
import ir.kaaveh.baadbaadaknews.data.local.FavoriteNewsDao
import ir.kaaveh.baadbaadaknews.data.remote.NewsAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    @Provides
    @Singleton
    fun provideFavoriteNewsDatabase(app: Application): FavoriteNewsDatabase =
        Room.databaseBuilder(
            app,
            FavoriteNewsDatabase::class.java,
            FavoriteNewsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideFavoriteNewsDao(db: FavoriteNewsDatabase): FavoriteNewsDao =
        db.newsDao

}