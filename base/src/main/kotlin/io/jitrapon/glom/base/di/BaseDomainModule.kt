package io.jitrapon.glom.base.di

import dagger.Module
import dagger.Provides
import io.jitrapon.glom.base.domain.circle.CircleDataSource
import io.jitrapon.glom.base.domain.circle.CircleInteractor
import io.jitrapon.glom.base.domain.circle.CircleRepository
import io.jitrapon.glom.base.domain.user.UserDataSource
import io.jitrapon.glom.base.domain.user.UserRemoteDataSource
import io.jitrapon.glom.base.domain.user.UserRepository
import javax.inject.Singleton

@Module
class BaseDomainModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserDataSource = UserRepository(UserRemoteDataSource())

    @Provides
    @Singleton
    fun provideCircleRepository(): CircleDataSource = CircleRepository()

    @Provides
    @Singleton
    fun provideCircleInteractor(dataSource: CircleDataSource): CircleInteractor = CircleInteractor(dataSource)
}