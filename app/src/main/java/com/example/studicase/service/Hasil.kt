package com.example.studicase.service

class Hasil <T> private constructor(val status: Status, val data: T?, val exception: Exception?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> success(data: T?): Hasil<T> {
            return Hasil(Status.SUCCESS, data, null)
        }
        fun <T> error(exception: Exception?): Hasil<T> {
            return Hasil(Status.ERROR, null, exception)
        }
        fun <T> loading(data: T?): Hasil<T> {
            return Hasil(Status.LOADING, data, null)
        }
    }
}