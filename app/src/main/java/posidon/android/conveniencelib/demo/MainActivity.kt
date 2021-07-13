package posidon.android.conveniencelib.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import posidon.android.conveniencelib.getNavigationBarHeight

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        update()
    }

    override fun onResume() {
        super.onResume()
        update()
    }

    fun update() {
        val n = getNavigationBarHeight()
        findViewById<TextView>(R.id.nav_bar_test_text).text = "navbar height: $n"
        findViewById<View>(R.id.nav_bar_test).updateLayoutParams {
            height = n
        }
    }
}