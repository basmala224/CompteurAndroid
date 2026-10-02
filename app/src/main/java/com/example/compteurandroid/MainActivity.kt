package com.example.compteurandroid

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variable qui contient la valeur du compteur
    private var compteur: Int = 0

    private lateinit var textViewCompteur: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupération des composants à partir de leurs identifiants
        textViewCompteur = findViewById(R.id.textViewCompteur)
        val buttonIncrementer = findViewById<Button>(R.id.buttonIncrementer)
        val buttonDecrementer = findViewById<Button>(R.id.buttonDecrementer)
        val buttonReinitialiser = findViewById<Button>(R.id.buttonReinitialiser)

        // (Facultatif) Restauration de la valeur après rotation de l'écran
        compteur = savedInstanceState?.getInt(CLE_COMPTEUR, 0) ?: 0
        actualiserAffichage()

        // Clic sur +
        buttonIncrementer.setOnClickListener {
            compteur++
            actualiserAffichage()
        }

        // Clic sur -
        buttonDecrementer.setOnClickListener {
            compteur--
            actualiserAffichage()
        }

        // Clic sur Réinitialiser
        buttonReinitialiser.setOnClickListener {
            compteur = 0
            actualiserAffichage()
            Toast.makeText(
                this,
                getString(R.string.message_reinitialisation),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Met à jour le TextView pour qu'il corresponde toujours à la variable compteur
    private fun actualiserAffichage() {
        textViewCompteur.text = compteur.toString()

        // (Facultatif) Couleur selon le signe de la valeur
        val couleur = when {
            compteur > 0 -> Color.rgb(0, 150, 0)   // vert
            compteur < 0 -> Color.RED              // rouge
            else -> Color.BLACK                    // noir
        }
        textViewCompteur.setTextColor(couleur)
    }

    // (Facultatif) Sauvegarde de la valeur avant la rotation de l'écran
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CLE_COMPTEUR, compteur)
    }

    companion object {
        private const val CLE_COMPTEUR = "cle_compteur"
    }
}