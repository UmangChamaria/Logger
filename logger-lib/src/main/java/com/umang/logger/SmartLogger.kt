package com.umang.logger

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log

/**
 * @author Umang Chamaria
 */


object SmartLogHelper {
  /**
   * Priority constant for the log method; use info().
   */
  val LOG_LEVEL_INFO = 1
  /**
   * Priority constant for the log method; use error().
   */
  val LOG_LEVEL_ERROR = 2
  /**
   * Priority constant for the log method; use warn().
   */
  val LOG_LEVEL_WARN = 3
  /**
   * Priority constant for the log method; use debug().
   */
  val LOG_LEVEL_DEBUG = 4
  /**
   * Priority constant for the log method; use verbose().
   */
  val LOG_LEVEL_VERBOSE = 5

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
   *
   * SmartLogHelper.LOG_TAG = "SampleApplication"
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

fun SmartLogger.info(message: String, throwable: Throwable? = null) {
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_INFO)
}

fun SmartLogger.verbose(message: String, throwable: Throwable? = null) {
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_VERBOSE)
}

fun SmartLogger.warn(message: String, throwable: Throwable? = null) {
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_WARN)
}

fun SmartLogger.debug(message: String, throwable: Throwable? = null) {
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_DEBUG)
}

fun SmartLogger.error(message: String, throwable: Throwable? = null) {
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_ERROR)
}

private fun getTag(clazz: Class<*>): String {
  val tag = clazz.simpleName
  return if (tag.length <= 23) {
    tag
  } else {
    tag.substring(0, 23)
  }
}

private fun logIfRequired(classname: String, message: String, throwable: Throwable?,
                          logLevel: Int) {
  if (SmartLogHelper.LOG_STATUS && SmartLogHelper.LOG_LEVEL >= logLevel) {
    if (throwable != null) {
      Log.i(SmartLogHelper.LOG_TAG, classname + " " + message, throwable)
    } else {
      Log.i(SmartLogHelper.LOG_TAG, classname + " " + message)
    }
  }
}