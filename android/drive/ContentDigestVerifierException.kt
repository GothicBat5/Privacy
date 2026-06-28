package me.proton.android.drive.verifier.domain.exception

sealed class ContentDigestVerifierException(message: String? = null,
    cause: Throwable? = null,
) : Throwable(message, cause) {

    class InvalidClaimed(message: String? = null,
        cause: Throwable? = null,
    ) : ContentDigestVerifierException(message, cause)

    class InvalidFile(message: String? = null,
        cause: Throwable? = null,
    ) : ContentDigestVerifierException(message, cause)

    class Mismatch(message: String? = null,
        cause: Throwable? = null,
    ) : ContentDigestVerifierException(message, cause)
}
