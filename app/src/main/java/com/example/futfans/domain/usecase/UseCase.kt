package com.example.futfans.domain.usecase

interface UseCase<Params, T> {
    suspend fun execute(params: Params? = null): T
}
