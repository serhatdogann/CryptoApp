package com.example.kripto.base

import android.annotation.SuppressLint
import com.example.kripto.model.errorResponse.ErrorResponse
import com.example.kripto.utils.NetworkResults
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

 abstract class BaseRepository {

    suspend fun <T>safeApiRequest(apirequest:suspend()->T):NetworkResults<T>{

        return withContext(Dispatchers.IO){
            try {
                NetworkResults.Success(apirequest.invoke())
            }catch (throwable:Throwable){

                when(throwable){

                    is HttpException->{
                        NetworkResults.Error(false,throwable.response()?.errorBody()?.toString())
                    }
                    else->NetworkResults.Error(true,throwable.localizedMessage)
                }

            }
        }
    }



}
@SuppressLint("SuspiciousIndentation")
private fun errorBodyResponse(error:String?):String{

    error.let {
        try {
        val errorResponse=Gson().fromJson(error,ErrorResponse::class.java)
        val errorMessage=errorResponse.status?.errorMessage
            errorMessage?:"Bilinmeyen Bir Hata Oluştu"

        }catch (throwable:Throwable){
            return "Bilinmeyen Bir Hata Oluştu"
        }

    }
    return "Bilinmeyen Bir Hata Oluştu"
}