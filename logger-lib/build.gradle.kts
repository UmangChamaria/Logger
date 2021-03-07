plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.dokka")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(28)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions.apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra.get("kotlinVersion")}")
}

tasks.dokkaHtml.configure {
    outputDirectory.set(rootDir.resolve("docs"))
}

apply(plugin = "com.vanniktech.maven.publish")