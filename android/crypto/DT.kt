package me.proton.core.drive.crypto.domain.repository

import me.proton.core.crypto.common.pgp.DecryptedText
import me.proton.core.domain.entity.UserId

interface DecryptedTextRepository {

    fun addDecryptedText(userId: UserId, keyId: String, decryptedText: DecryptedText)
    fun getDecryptedText(userId: UserId, keyId: String): DecryptedText?
    fun removeAll(userId: UserId)
}
