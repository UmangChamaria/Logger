package com.umang.logger.example

import android.app.Application
import com.umang.logger.SmartLogHelper

class SampleApplication: Application() {
  override fun onCreate() {
    super.onCreate()
    SmartLogHelper.initializeLogger(applicationContext)
    SmartLogHelper.LOG_LEVEL = SmartLogHelper.LOG_LEVEL_VERBOSE
    SmartLogHelper.LOG_TAG = "SampleApplication"
  }
}