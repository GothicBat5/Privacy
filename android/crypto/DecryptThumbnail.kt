package me.proton.core.drive.crypto.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.proton.core.drive.base.domain.extension.toResult
import me.proton.core.drive.base.domain.util.coRunCatching
import me.proton.core.drive.cryptobase.domain.usecase.DecryptData
import me.proton.core.drive.key.domain.extension.keyHolder
import me.proton.core.drive.key.domain.usecase.GetContentKey
import me.proton.core.drive.link.domain.entity.FileId
import me.proton.core.drive.link.domain.usecase.GetLink
import java.io.InputStream
import javax.inject.Inject

@ExperimentalCoroutinesApi
class DecryptThumbnail @Inject constructor(private val getLink: GetLink,
    private val getContentKey: GetContentKey,
    private val decryptData: DecryptData,
) {

    suspend operator fun invoke(fileId: FileId,
        inputStream: InputStream,
    ): Result<ByteArray> = coRunCatching {
        val file = getLink(fileId).toResult().getOrThrow()
        val nodeKey = getContentKey(file).getOrThrow()
        inputStream.use {
            decryptData(
                decryptKey = nodeKey.decryptKey.keyHolder,
                keyPacket = nodeKey.encryptedKeyPacket,
                data = inputStream.readBytes(),
            ).getOrThrow()
        }
    }
}
