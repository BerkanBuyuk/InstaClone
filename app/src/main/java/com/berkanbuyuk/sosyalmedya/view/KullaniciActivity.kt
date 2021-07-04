package com.berkanbuyuk.sosyalmedya.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.berkanbuyuk.sosyalmedya.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class KullaniciActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser
        if (guncelKullanici != null){
            val intent = Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun girisYap(view: View){
        val email = emailText.text.toString()
        val sifre = passwordText.text.toString()

        auth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val guncelKullanici = auth.currentUser?.email.toString()
                val intent = Intent(this, HaberlerActivity::class.java)
                Toast.makeText(this,"Hoşgeldin : ${email}",Toast.LENGTH_LONG).show()
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }

    fun kayitOl(view: View){
        val intent = Intent(this, KayitActivity::class.java)
        startActivity(intent)
    }
}




