package ch.protonmail.android.api

import ch.protonmail.android.BuildConfig
import ch.protonmail.android.mailsession.domain.model.RustApiConfig
import ch.protonmail.android.useragent.BuildUserAgent
import me.proton.core.configuration.EnvironmentConfigurationDefaults
import okhttp3.HttpUrl
import uniffi.mail_uniffi.ApiEnvId
import javax.inject.Inject

class MailRustApiConfig @Inject constructor(private val buildUserAgent: 
    BuildUserAgent,
    private val baseApiUrl: HttpUrl
) : RustApiConfig {

    override val isDebug: Boolean
        get() = BuildConfig.DEBUG
    override val platform: String
        get() = "android"
    override val product: String
        get() = "mail"
    override val appVersion: String
        get() = BuildConfig.VERSION_NAME
    override val userAgent: String
        get() = buildUserAgent()
    override val proxy: String?
        get() = null
    override val envId: ApiEnvId
        get() = baseApiUrl.host.toApiEnv()

    private fun String.toApiEnv(): ApiEnvId {
        val apiEnvId = when {
            endsWith("${EnvironmentConfigurationDefaults.apiPrefix}.proton.black") -> ApiEnvId.Atlas
            endsWith("proton.black") -> ApiEnvId.Scientist(split(".")[1])
            else -> ApiEnvId.Prod
        }
        return apiEnvId
    }
}
