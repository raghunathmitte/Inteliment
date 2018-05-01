package au.com.inteliment.poc.di

import au.com.inteliment.poc.viewmodel.Test2ViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(test2ViewModel: Test2ViewModel)
}