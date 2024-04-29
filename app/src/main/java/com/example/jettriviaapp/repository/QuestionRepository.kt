package com.example.jettriviaapp.repository

import android.util.Log
import com.example.jettriviaapp.data.DataOrException
import com.example.jettriviaapp.model.QuestionItem
import com.example.jettriviaapp.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val api: QuestionApi) {
    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception>{
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.toString().isNotEmpty()) dataOrException.loading = false
        }catch (exception: Exception){
            dataOrException.e = exception
            Log.d("EXC", "getAllQuestion: ${dataOrException.e!!.localizedMessage}")
        }
        return dataOrException
    }
}