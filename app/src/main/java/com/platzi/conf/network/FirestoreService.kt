package com.platzi.conf.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.platzi.conf.model.Conference
import com.platzi.conf.model.Speaker
import com.platzi.conf.network.Callback as Callback1

const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

//    init {
//        firebaseFirestore.firestoreSettings = settings
//    }

    fun getSpeakers(callback: Callback1<List<Speaker>>) {

        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                Log.e("PlatziSChedule","Datos obtenidos exitoso...")
                for (doc in result) {
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchedule(callback: Callback1<List<Conference>>) {
        Log.e("PlatziSChedule","Obteniendo datos Servicio Conferencias...")

        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .orderBy("datetime")
            .get()
            .addOnSuccessListener { result ->
                Log.e("PlatziSChedule","Datos Conferencias obtenidos exitoso...")
                for (doc in result) {
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }

            .addOnFailureListener{exception->
                Log.w("PlatziSChedule","Error getting documents.",exception)
            }


    }

}