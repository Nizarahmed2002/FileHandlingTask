plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.filehandlingtask"
    compileSdk = 34

    viewBinding.enable = true

    defaultConfig {
        applicationId = "com.example.filehandlingtask"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //CoroutineScope
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")
    //LifeCycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    //RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.2.0")
    //coil image setter
    implementation ("io.coil-kt:coil:2.4.0")
    //gson for json
    implementation ("com.google.code.gson:gson:2.8.8") // You might need to adjust the version.



}