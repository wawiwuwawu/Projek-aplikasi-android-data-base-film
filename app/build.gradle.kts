plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}




android {


    namespace = "com.si23b.aplikasi_database_film2"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/INDEX.LIST",
                "META-INF/*.LIST",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/NOTICE",

                // File spesifik Netty
                "META-INF/io.netty.versions.properties",
                "META-INF/**/io.netty.versions.properties" // Untuk semua subdirektori
            )
        }
    }


    defaultConfig {
        applicationId = "com.si23b.aplikasi_database_film2"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.appdistribution.gradle)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Material Design
    implementation ("com.google.android.material:material:1.6.0")

    // Untuk activityViewModels()
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    implementation ("androidx.activity:activity-ktx:1.7.2")

    // CardView
    implementation ("androidx.cardview:cardview:1.0.0")


    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.appcompat:appcompat:1.6.1") // Untuk SearchView

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // ViewModel & LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    // Image Loading
    implementation ("io.coil-kt:coil:2.2.2")
    implementation ("io.coil-kt:coil:2.6.0")

    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")



}