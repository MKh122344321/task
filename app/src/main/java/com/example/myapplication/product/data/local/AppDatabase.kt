package com.example.myapplication.product.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LocalProduct::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val daoo: ProductDao

    companion object {
        @Volatile
        private var appdatabaseinstane: ProductDao? =null
        private fun buildDataBase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context = context.applicationContext,
                AppDatabase::class.java,
                name = "shop_database"
            ).fallbackToDestructiveMigration().build()
        }

        fun getDataBaseInstance(context: Context): ProductDao {
            synchronized(lock = this){
                if (appdatabaseinstane == null) {
                    appdatabaseinstane = buildDataBase(context = context).daoo
                }
                return appdatabaseinstane as ProductDao
            }

        }
    }
}
//companion object {
//    @Volatile
//    private var INSTANCE: AppDatabase? = null
//
//    fun getDatabase(context: Context): AppDatabase {
//        return INSTANCE ?: synchronized(this) {
//            val instance = Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "product_db"
//            ).build()
//            INSTANCE = instance
//            instance
//        }
//    }
//}