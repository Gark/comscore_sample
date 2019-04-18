package com.example.comescoreapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.comscore.Analytics
import com.comscore.PublisherConfiguration

private const val COMSCORE_PERSISTENT_LABEL = "ns_site"

private const val COMSCORE_NB_IDFA = "nb_idfa"

private const val COMSCORE_PERSISTENT_VALUE = "total"
private const val PUBLISHER_ID = "11464450"
private const val SECRET = "f6b0b5064016a7d3ee6bc3ef3303fb5c"
private const val COMSCORE_NB_IDFA_VALUE =  "COMSCORE_NB_IDFA"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val publisherConfig = PublisherConfiguration.Builder()
            .publisherId(PUBLISHER_ID)
            .publisherSecret(SECRET)
            .secureTransmission(true)
            .build()

        Analytics.getConfiguration().apply {
            addClient(publisherConfig)
            setPersistentLabel(COMSCORE_PERSISTENT_LABEL, COMSCORE_PERSISTENT_VALUE)
            setPersistentLabel(COMSCORE_NB_IDFA, COMSCORE_NB_IDFA_VALUE)
        }

        Analytics.start(applicationContext)

        findViewById<Button>(R.id.button).setOnClickListener {
            val map = HashMap<String, String>()
            map["key"] = "value${System.currentTimeMillis()}"
            Analytics.notifyViewEvent(map)
        }

    }

    override fun onResume() {
        super.onResume()
        Analytics.notifyEnterForeground()
    }

    override fun onPause() {
        super.onPause()
        Analytics.notifyExitForeground()
    }
}
