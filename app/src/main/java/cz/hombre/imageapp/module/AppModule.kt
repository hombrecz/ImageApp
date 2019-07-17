package cz.hombre.imageapp.module

import cz.hombre.imageapp.services.EncodingService
import cz.hombre.imageapp.services.RestService
import cz.hombre.imageapp.services.impl.EncodingServiceImpl
import cz.hombre.imageapp.services.impl.RestServiceImpl
import org.koin.dsl.module.module

val appModule = module {
    single<EncodingService> { EncodingServiceImpl() }
    single<RestService> { RestServiceImpl() }
}