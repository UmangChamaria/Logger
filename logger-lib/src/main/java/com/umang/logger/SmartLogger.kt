package com.umang.logger

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log

/**
 * Helper class to initialise the library and set the logging level.
 * @author Umang Chamaria
 */
object SmartLogHelper {
    /**
     * Priority constant for the log method; use info().
     */
    const val LOG_LEVEL_INFO = 1

    /**
     * Priority constant for the log method; use error().
     */
    const val LOG_LEVEL_ERROR = 2

    /**
     * Priority constant for the log method; use warn().
     */
    const val LOG_LEVEL_WARN = 3

    /**
     * Priority constant for the log method; use debug().
     */
    const val LOG_LEVEL_DEBUG = 4

    /**
     * Priority constant for the log method; use verbose().
     */
    const val LOG_LEVEL_VERBOSE = 5

    /**
     * Default log status.
     * Set to true when initializeLogger() is called and the build type is debug.
     * To enable Logs for signed builds set this field as true.
     *
     * SmartLogHelper.LOG_STATUS = true
     */
    var LOG_STATUS = false

    /**
     * Default log tag.
     * Can be overridden by using
     * <b>Note:</b> Tag length should not be greater than 23.
     * @sample SmartLogHelper.LOG_TAG = "SampleApplication"
     */
    var LOG_TAG = "SmartLogger"

    /**
     * Log level for logging
     *
     * Default log level LOG_LEVEL_INFO
     */
    var LOG_LEVEL = LOG_LEVEL_INFO

    /**
     * Initialize the smart logger SDK to print logs when it is a debug build.
     * @param context Application context
     */
    fun initializeLogger(context: Context) {
        try {
            val isDebug = (0 != (context.applicationInfo).flags and ApplicationInfo.FLAG_DEBUGGABLE)
            if (isDebug) SmartLogHelper.LOG_STATUS = true
        } catch (e: Exception) {
            Log.e("SmartLogger", "initializeLogger() Exception: ", e)
        }
    }

}

interface SmartLogger {
    val className: String
        get() = getTag(javaClass)
}

/**
 * Equivalent of [Log.i]
 * @param message Message to be logged
 * @param throwable throwable object if any
 */
fun SmartLogger.info(message: String, throwable: Throwable? = null) {
    if (SmartLogHelper.LOG_STATUS && SmartLogHelper.LOG_LEVEL >= SmartLogHelper.LOG_LEVEL_INFO) {
        if (throwable != null) {
            Log.i(SmartLogHelper.LOG_TAG, "$className $message", throwable)
        } else {
            Log.i(SmartLogHelper.LOG_TAG, "$className $message")
        }
    }
}

/**
 * Equivalent of [Log.v]
 * @param message Message to be logged
 * @param throwable throwable object if any
 */
fun SmartLogger.verbose(message: String, throwable: Throwable? = null) {
    if (SmartLogHelper.LOG_STATUS && SmartLogHelper.LOG_LEVEL >= SmartLogHelper.LOG_LEVEL_VERBOSE) {
        if (throwable != null) {
            Log.v(SmartLogHelper.LOG_TAG, "$className $message", throwable)
        } else {
            Log.v(SmartLogHelper.LOG_TAG, "$className $message")
        }
    }
}

/**
 * Equivalent of [Log.w]
 * @param message Message to be logged
 * @param throwable throwable object if any
 */
fun SmartLogger.warn(message: String, throwable: Throwable? = null) {
    if (SmartLogHelper.LOG_STATUS && SmartLogHelper.LOG_LEVEL >= SmartLogHelper.LOG_LEVEL_WARN) {
        if (throwable != null) {
            Log.w(SmartLogHelper.LOG_TAG, "$className $message", throwable)
        } else {
            Log.w(SmartLogHelper.LOG_TAG, "$className $message")
        }
    }
}

/**
 * Equivalent of [Log.d]
 * @param message Message to be logged
 * @param throwable throwable object if any
 */
fun SmartLogger.debug(message: String, throwable: Throwable? = null) {
    if (SmartLogHelper.LOG_STATUS && SmartLogHelper.LOG_LEVEL >= SmartLogHelper.LOG_LEVEL_DEBUG) {
        if (throwable != null) {
            Log.d(SmartLogHelper.LOG_TAG, "$className $message", throwable)
        } else {
            Log.d(SmartLogHelper.LOG_TAG, "$className $message")
        }
    }
}

/**
 * Equivalent of [Log.e]
 * @param message Message to be logged
 * @param throwable throwable object if any
 */
fun SmartLogger.error(message: String, throwable: Throwable? = null) {
    if (SmartLogHelper.LOG_STATUS && SmartLogHelper.LOG_LEVEL >= SmartLogHelper.LOG_LEVEL_ERROR) {
        if (throwable != null) {
            Log.e(SmartLogHelper.LOG_TAG, "$className $message", throwable)
        } else {
            Log.e(SmartLogHelper.LOG_TAG, "$className $message")
        }
    }
}

private fun getTag(clazz: Class<*>): String {
    val tag = clazz.simpleName
    return if (tag.length <= 23) {
        tag
    } else {
        tag.substring(0, 23)
    }
}