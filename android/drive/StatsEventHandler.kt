package me.proton.android.drive.stats

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.proton.core.domain.entity.UserId
import me.proton.core.drive.announce.event.domain.entity.Event
import me.proton.core.drive.announce.event.domain.entity.Event.BackupCompleted
import me.proton.core.drive.announce.event.domain.entity.Event.BackupStarted
import me.proton.core.drive.announce.event.domain.entity.Event.Upload
import me.proton.core.drive.announce.event.domain.handler.EventHandler
import javax.inject.Inject

class StatsEventHandler @Inject constructor(private val backupCompletedSideEffect: 
    BackupCompletedSideEffect,
    private val backupStartedSideEffect: BackupStartedSideEffect,
    private val uploadSideEffect: UploadSideEffect,
    private val uploadDocumentSideEffect: UploadDocumentSideEffect,
) : EventHandler {

    private val mutex = Mutex()
    override suspend fun onEvent(
        userId: UserId,
        event: Event,
    ) = mutex.withLock {
        when (event) {
            is BackupStarted -> backupStartedSideEffect(event)
            is BackupCompleted -> backupCompletedSideEffect(event)
            is Upload -> {
                uploadSideEffect(event)
                uploadDocumentSideEffect(event)
            }
            else -> Unit
        }
    }
}
