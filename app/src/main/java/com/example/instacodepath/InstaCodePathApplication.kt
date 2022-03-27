package com.example.instacodepath

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject

class InstaCodePathApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // register the post object
        ParseObject.registerSubclass(Post::class.java)

        // initialize the parse library
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
    }
}