package com.platzi.conf.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.conf.model.Conference
import com.platzi.conf.network.Callback
import com.platzi.conf.network.FirestoreService
import java.lang.Exception
import javax.annotation.Nullable

class ScheduleViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase() {
        Log.e("PlatziSChedule","Obteniendo datos Viewmodel...")
        firestoreService.getSchedule(object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(@NonNull exception: Exception ) {
                processFinished()
                //Toast.makeText(this, "error al leer doc", Toast.LENGTH_SHORT).show()

                Log.e("PlatziSChedule","Error getting documents.",exception)
            }
        })
    }

    fun processFinished() {
        isLoading.value = true
    }
}