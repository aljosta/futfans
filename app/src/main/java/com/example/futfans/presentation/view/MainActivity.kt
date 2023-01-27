package com.example.futfans.presentation.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.futfans.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addFragment(containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            replace(containerViewId, fragment)
            addToBackStack(null)
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(
        text = "Hola $text",
        modifier = Modifier
            .clickable(onClick = {})
            .padding(all = 24.dp),
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun ButtonExample() {
    val context = LocalContext.current

    Button(
        onClick = {
            Toast.makeText(context, "Hice Click en el botón", Toast.LENGTH_SHORT).show()
        }
    ) {
    }
}
