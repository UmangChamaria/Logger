package com.umang.logger

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Log

/**
 * @author Umang Chamaria
 */


object SmartLogHelper{

  val LOG_LEVEL_INFO = 1

  val LOG_LEVEL_ERROR = 2

  val LOG_LEVEL_WARN = 3

  val LOG_LEVEL_DEBUG = 4

  val LOG_LEVEL_VERBOSE = 5

  var LOG_STATUS = false

  var LOG_TAG = "SmartLogger"

  var LOG_LEVEL = LOG_LEVEL_INFO

  fun initializeLogger(context: Context){
    try {
      val isDebug =   (0 != (context.applicationInfo).flags and ApplicationInfo.FLAG_DEBUGGABLE)
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

fun SmartLogger.info(message: String, throwable: Throwable? = null){
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_INFO)
}

fun SmartLogger.verbose(message: String, throwable: Throwable? = null){
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_VERBOSE)
}

fun SmartLogger.warn(message: String, throwable: Throwable? = null){
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_WARN)
}

fun SmartLogger.debug(message: String, throwable: Throwable? = null){
  logIfRequired(className, message, throwable, SmartLogHelper.LOG_LEVEL_DEBUG)
}

fun SmartLogger.error(message: String, throwable: Throwable? = null){
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

private fun logIfRequired(classname: String, message: String, throwable: Throwable?, logLevel: Int){
  if (SmartLogHelper.LOG_STATUS && SmartLogHelper.LOG_LEVEL >= logLevel){
    if (throwable != null){
      Log.i(SmartLogHelper.LOG_TAG, classname + " " + message, throwable)
    }else{
      Log.i(SmartLogHelper.LOG_TAG, classname + " " + message)
    }
  }
}