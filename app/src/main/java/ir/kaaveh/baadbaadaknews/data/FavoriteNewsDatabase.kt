package ir.kaaveh.baadbaadaknews.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.kaaveh.baadbaadaknews.data.local.FavoriteNewsDao
import ir.kaaveh.baadbaadaknews.data.local.FavoriteNewsDto

@Database(
    entities = [FavoriteNewsDto::class],
    version = 1,
)
abstract class FavoriteNewsDatabase: RoomDatabase() {

    abstract val newsDao: FavoriteNewsDao

    companion object {
        const val DATABASE_NAME = "news_db"
    }
}