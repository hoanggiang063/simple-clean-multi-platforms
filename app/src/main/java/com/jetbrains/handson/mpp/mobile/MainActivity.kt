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
import com.architecture.repository.demo.repository.RemoteToDoRepositoryImpl
import com.architecture.repository.demo.service.Webservice
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.core.service.PlatformService.httpClientEngine
import com.jetbrains.handson.mpp.mobile.com.architecture.repository.demo.remote.features.todo.service.WebServiceImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var webservice: Webservice = WebServiceImpl(httpClientEngine);

        var toDoRepository: ToDoRepository =
            RemoteToDoRepositoryImpl(webservice, BaseExceptionMapperImpl(),
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
