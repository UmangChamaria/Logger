package com.umang.logger

import android.app.Application

/**
 * @author Umang Chamaria
 */
class SampleApplication:Application() {
  override fun onCreate() {
    super.onCreate()
    SmartLogHelper.initializeLogger(applicationContext)
    SmartLogHelper.LOG_LEVEL = SmartLogHelper.LOG_LEVEL_VERBOSE
    SmartLogHelper.LOG_TAG = "SampleApplication"
  }
}