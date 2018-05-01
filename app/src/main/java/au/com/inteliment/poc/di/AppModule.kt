package au.com.inteliment.poc.di

import au.com.inteliment.poc.IntelimentApplication
import dagger.Module
import dagger.Provides

@Module
open class AppModule(private var application: IntelimentApplication) {

    @Provides
    protected open fun provideApplication(): IntelimentApplication = application

}