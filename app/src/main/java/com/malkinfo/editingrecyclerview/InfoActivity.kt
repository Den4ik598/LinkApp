package com.malkinfo.editingrecyclerview

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malkinfo.editingrecyclerview.model.ClassLink
import org.w3c.dom.Text

class infoActivity : AppCompatActivity() {

    private lateinit var TextProtocol:TextView
    private lateinit var TextAppname: TextView
    private lateinit var TextUuid: TextView
    private lateinit var TextAdress: TextView
    private lateinit var TextPort: TextView
    private lateinit var TextSecurity:TextView
    private lateinit var TextSni: TextView
    private lateinit var TextPublic_key: TextView
    private lateinit var TextBrowser: TextView
    private lateinit var TextType: TextView
    private lateinit var TextFlow: TextView
    private lateinit var TextCountry: TextView

    private lateinit var Link: ClassLink


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.infoitem)
        val addlink = intent.getStringExtra("Link")
        Link = Gson().fromJson(addlink, object : TypeToken<ClassLink>() {}.type)
        TextProtocol = findViewById(R.id.TextProtocol)
        TextAppname = findViewById(R.id.TextAppname)
        TextUuid = findViewById(R.id.TextUuid)
        TextAdress = findViewById(R.id.TextAdress)
        TextPort = findViewById(R.id.TextPort)
        TextSecurity = findViewById(R.id.TextSecurity)
        TextSni = findViewById(R.id.TextSni)
        TextPublic_key = findViewById(R.id.TextPublic_key)
        TextBrowser = findViewById(R.id.TextBrowser)
        TextType = findViewById(R.id.TextType)
        TextFlow = findViewById(R.id.TextFlow)
        TextCountry = findViewById(R.id.TextCountry)
        val backButton = findViewById<Button>(R.id.buttonBack)

        TextProtocol.text = Link.Protocol
        TextAppname.text = Link.Appname
        TextUuid.text = Link.Uuid
        TextAdress.text = Link.Adress
        TextPort.text = Link.Port.toString()
        TextSecurity.text = Link.Security
        TextSni.text = Link.Sni
        TextPublic_key.text = Link.Public_Key
        TextBrowser.text = Link.Browser
        TextType.text = Link.Type
        TextFlow.text = Link.Flow
        TextCountry.text = Link.Country

        val TextViews = listOf(TextProtocol,TextAppname, TextUuid,TextAdress, TextPort,  TextSecurity,TextSni, TextPublic_key, TextBrowser, TextType, TextFlow,TextCountry)


        TextViews.forEach { textView ->
            textView.setOnLongClickListener {
                val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("text", textView.text)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(this, "Текст скопирован", Toast.LENGTH_SHORT).show()
                true
            }
        }


        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}