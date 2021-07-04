package com.berkanbuyuk.sosyalmedya.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.berkanbuyuk.sosyalmedya.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_kayit.*

class KayitActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)
        auth = FirebaseAuth.getInstance()
    }

    fun kayitOl2(view: View){
        val email2 = emailText2.text.toString()
        val sifre2 = passwordText2.text.toString()

        auth.createUserWithEmailAndPassword(email2,sifre2).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val intent = Intent(this, HaberlerActivity::class.java)
                Toast.makeText(this,"Kayıt Başarılı !",Toast.LENGTH_LONG).show()
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
}