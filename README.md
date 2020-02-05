# SmartLogger
Small utility library for for logging. This is just because was lazy to copy the same file in every project.

![Download](https://api.bintray.com/packages/umangchamaria/umang/loggerLib/images/download.svg)


# Features:

* Consistent log tag for all logs.
* Enable specific log level.
* Classname of the method included by defualt.
* Need not bother about disabling logs for signed build.

# Usage

**Installation**<br/>

To use the logger library add the below dependency in the app level build.gradle 

```
implementation 'com.umang:logger-lib:+'
```

**Initialize Logger**<br/>
 
Before you start using the SmartLogger it needs to initialized. This is requried because SmartLogger prints logs only if the application is in debug mode, if it is a signed build the SmartLogger does not print any logs. Though logs can be enabled for signed build as well, described later. To initialize the logger add the below line of code in the application class.

```
  SmartLogHelper.initializeLogger(applicationContext)
```

**Set log level**<br/>

   With SmartLogger one can enable logs based on the log levels.
This eases readability of the logs and you only see the kind of logs you want to see.
By default the log level is set to Info and can be changed by setting the log level in the application class.

```
  SmartLogHelper.LOG_LEVEL = SmartLogHelper.LOG_LEVEL_VERBOSE
```

**Set log tag**<br/>

Setting a specific log tag for all the logs helps in filtering the logs of your application/SDK. By default the tag is set to 
`SmartLogger`. You can set the tag based on your application/SDK name.Below is an example on how to set the log tag. The tag needs to be set in the application class.

```
  SmartLogHelper.LOG_TAG = "SampleApplication"
```

**Enable log for signed builds**<br/>
By default logs are disabled for signed builds. In case you want to enable logs for signed builds you can do so by adding the below code in the application class

```
  SmartLogHelper.LOG_STATUS = true
```

**Adding log to your class**

```
class MainActivity : AppCompatActivity(),SmartLogger {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    info("info log")
    error("error log")
    warn("warning log")
    debug("debug log")
    verbose("verbose log")
  }
}
```

Documentation - https://umang91.github.io/SmartLogger/
