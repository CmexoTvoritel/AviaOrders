package com.effmobile.aviaorders.domain.usecase.main

import com.effmobile.aviaorders.domain.repository.main.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke() = repository.getOffersInfo()
}