package com.example.mymarvel.di

//@Module
//@InstallIn(SingletonComponent::class)
//object LocalModule {
//
//    @Singleton
//    @Provides
//    fun providesRoom(@ApplicationContext context: Context): MarvelDatabase {
//        return Room.databaseBuilder(context, MarvelDatabase::class.java, MARVEL_DATABASE)
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun providesMarvelDao(marveldb: MarvelDatabase) : MarvelDao {
//        return marveldb.marvelDao()
//    }
//
//}