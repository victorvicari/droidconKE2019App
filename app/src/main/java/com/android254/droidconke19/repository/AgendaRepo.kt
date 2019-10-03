package com.android254.droidconke19.repository

import com.android254.droidconke19.datastates.Result
import com.android254.droidconke19.models.AgendaModel
import com.android254.droidconke19.utils.await
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects

interface AgendaRepo {
    suspend fun agendaData(): Result<List<AgendaModel>>
}

class AgendaRepoImpl(val firestore: FirebaseFirestore) : AgendaRepo {

    override suspend fun agendaData(): Result<List<AgendaModel>> {
        return try {
            val snapshot = firestore.collection("agenda_2019").orderBy("id", Query.Direction.ASCENDING).get().await()
            return Result.Success(snapshot.toObjects())

        } catch (e: FirebaseFirestoreException) {
            Result.Error(e.message)
        }

    }
}
