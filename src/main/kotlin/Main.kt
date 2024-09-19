import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.network.SupaProvider
import data.repository.RequestsRepositoryImpl
import data.utils.MyPref
import domain.repository.RequestsRepository
import domain.usecase.GetMainStates
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.DashboardApp
import presentation.home.HomeViewModel
import presentation.keys.KeysViewModel


val SharedModules = module {
    single {
        MyPref()
    }
    single {
        KeysViewModel(get())
    }
    single {
        SupaProvider(get())
    }
    single<RequestsRepository> {
        RequestsRepositoryImpl(get())
    }
    single {
        HomeViewModel(get(),get())
    }
    single { GetMainStates(get()) }
}

fun main() = application {
    startKoin {
        modules(SharedModules)
    }
    Window(onCloseRequest = ::exitApplication) {
        DashboardApp()
    }
}
