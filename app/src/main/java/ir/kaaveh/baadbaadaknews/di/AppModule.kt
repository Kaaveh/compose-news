package ir.kaaveh.baadbaadaknews.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.kaaveh.baadbaadaknews.common.Constants.Companion.KABARONLINE_BASE_URL
import ir.kaaveh.baadbaadaknews.common.Constants.Companion.NEWS_ORG_BASE_URL
import ir.kaaveh.baadbaadaknews.data.FavoriteNewsDatabase
import ir.kaaveh.baadbaadaknews.data.local.FavoriteNewsDao
import ir.kaaveh.baadbaadaknews.data.remote.khabaronline.KhabaronlineAPI
import ir.kaaveh.baadbaadaknews.data.remote.news_org.NewsAPI
import ir.kaaveh.baadbaadaknews.data.repository.NewsRepositoryImpl
import ir.kaaveh.baadbaadaknews.domain.repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsAPI = Retrofit.Builder()
        .baseUrl(NEWS_ORG_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    @Provides
    @Singleton
    fun provideKabaronlineApi(): KhabaronlineAPI = Retrofit.Builder()
        .baseUrl(KABARONLINE_BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
        .create(KhabaronlineAPI::class.java)

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

    @Provides
    @Singleton
    fun provideJsonNewsRepository(
        orgAPI: NewsAPI,
        khabaronlineAPI: KhabaronlineAPI,
        dao: FavoriteNewsDao,
    ): NewsRepository =
        NewsRepositoryImpl(orgAPI = orgAPI, khabaronlineAPI = khabaronlineAPI, dao = dao)

}