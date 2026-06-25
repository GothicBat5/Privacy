package ch.protonmail.android.util

import me.proton.core.util.kotlin.Logger
import org.jetbrains.annotations.NonNls
import timber.log.Timber

internal object TimberLogger : Logger {

    override fun e(tag: String, message: String) 
    {
        Timber.tag(tag).e(message)
    }

    override fun e(tag: String, e: Throwable)
    {
        Timber.tag(tag).e(e)
    }

    override fun e(tag: String, e: Throwable,
        @NonNls message: String
    ) 
    {
        Timber.tag(tag).e(e, message)
    }

    override fun w(tag: String, message: String) 
    {
        Timber.tag(tag).w(message)
    }

    override fun w(tag: String, e: Throwable) 
    {
        Timber.tag(tag).w(e)
    }

    override fun w(tag: String, e: Throwable,
        @NonNls message: String) 
    {
        Timber.tag(tag).w(e, message)
    }

    override fun i(tag: String, @NonNls message: String) 
    {
        Timber.tag(tag).i(message)
    }

    override fun i(tag: String, e: Throwable, message: String) 
    {
        Timber.tag(tag).i(e, message)
    }

    override fun d(tag: String, message: String) 
    {
        Timber.tag(tag).d(message)
    }

    override fun d(tag: String, e: Throwable, message: String) 
    {
        Timber.tag(tag).d(e, message)
    }

    override fun v(tag: String, message: String) 
    {
        Timber.tag(tag).v(message)
    }

    override fun v(tag: String, e: Throwable, message: String) 
    {
        Timber.tag(tag).v(e, message)
    }
}
