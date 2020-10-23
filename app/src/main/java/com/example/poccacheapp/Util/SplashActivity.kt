package com.example.poccacheapp.Util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.poccacheapp.MainActivity
import com.example.poccacheapp.R
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


/*
class SplashActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        PrefetchData()
    }

}
*/

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //splash_logo.setImageResource(R.drawable.ic_launcher_background)
        getInfo(text)
        /*Handler().postDelayed(
            {
                PrefetchData().execute()
            }, 5000)*/

    }

    fun getInfo(view: View){
        if (NetworkAvail()){
            splash_logo.setImageResource(R.drawable.capture)
            PrefetchData().execute("https://run.mocky.io/v3/831a7dc1-800c-4757-8547-e844b7a9d24a")
        }
        else{
            text.text = "No Network"
        }
    }
    fun NetworkAvail(): Boolean{
        val cManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cManager.activeNetwork

        if (network!=null){
            return true
        }
        return false
    }
    /**
     * Async Task to make http call
     */
    private inner class PrefetchData :
        AsyncTask<String, Void?, Void?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            // before making http calls
        }

        override fun doInBackground(vararg params: String?): Void? {

            httpGet(params[0])
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }
    }
    private fun httpGet(myURL: String?): String {

        val inputStream: InputStream
        val result:String

        // create URL
        val url:URL = URL(myURL)

        // create HttpURLConnection
        val conn:HttpURLConnection = url.openConnection() as HttpURLConnection

        // make GET request to the given URL
        conn.connect()

        // receive response as inputStream
        inputStream = conn.inputStream

        // convert inputstream to string
        if(inputStream != null) {
            result = inputStream.bufferedReader().use(BufferedReader::readText)
        }
        else
            result = " "

        return result
    }
}










/*
private val _properties1 = MutableLiveData<List<api>>()
val properties1: LiveData<List<api>>
    get() = _properties1

private val _status = MutableLiveData<AllApiStatus1>()
val status: LiveData<AllApiStatus1>
    get() = _status
*/