package com.jetbrains.handson.mpp.mobile

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.architecture.business.demo.callback.ToDoCallBack
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.business.demo.usecase.ToDoUseCase
import com.architecture.business.demo.usecase.ToDoUseCaseImpl
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.demo.model.TodoModel
import com.architecture.repository.demo.repository.RemoteToDoRepositoryImpl
import com.architecture.repository.demo.service.Webservice
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.demo.remote.features.todo.service.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toDoRepository: ToDoRepository =
            RemoteToDoRepositoryImpl(
                object : Webservice {
                    override fun getTodo(todoId: Int): TodoModel {
                        var todo: TodoModel?
                        var call: Call<TodoModel> =
                            RetrofitFactory.makeRetrofitService().getTodo(todoId);
                        todo = call.execute().body();
                        if (todo == null)
                            throw Exception()
                        return todo;
                    }

                },
                BaseExceptionMapperImpl(),
                object : com.architecture.repository.demo.repository.Log {
                    override fun log(error: Throwable) {
                        Log.v("vhgiang", "error:" + error?.printStackTrace())
                    }

                }
            )
        var todoUseCase: ToDoUseCase = ToDoUseCaseImpl(toDoRepository)

        val progress = ProgressDialog(this@MainActivity)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false)

        // load data by network (remote)
        btnRemote.setOnClickListener {
            progress.show()
            todoUseCase.buildUseCase(1)
            var job: Job = todoUseCase.execute(object : ToDoCallBack {
                override fun onSuccess(expectedResult: ToDoInfo?) {
                    Log.v("vhgiang", "=> remote solution run pass: " + expectedResult.toString())
                    progress.dismiss()
                }

                override fun onFail(throwable: Throwable) {
                    Log.v("vhgiang", "=> remote solution run fail: " + throwable?.message)
                    progress.dismiss()
                }

            })
        }
    }
}

object RetrofitFactory {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    fun makeRetrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                ).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}