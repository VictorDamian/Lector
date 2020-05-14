package com.example.lectorvoz

/**
 * powered by: VMDB
 * **/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//TTS
import android.speech.tts.TextToSpeech;
//motro sintetizador
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
//ejecuta las instrucciones al inicar el moto TextToSpeech
import java.util.Locale;

class MainActivity : AppCompatActivity(), OnInitListener {
    private var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Objeto
        tts = TextToSpeech(this@MainActivity, this)
        button1!!.setOnClickListener{
            hearMss()
        }
    }

    //se inicia el objeto, el metodo que se jecuta cuando se crea la instancia TextToSpeeach
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            var result = tts!!.setLanguage(Locale.getDefault())
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(applicationContext, R.string.error_leng, Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(applicationContext, R.string.failed, Toast.LENGTH_LONG).show()
        }
    }
    private fun speak(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    private fun hearMss(){
        speak("Message test")
    }
}
