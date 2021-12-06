package com.mintic.myaddressbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.util.ArrayList
import org.json.JSONArray
import org.json.JSONException

class ListActivity : AppCompatActivity() {

    private lateinit var mContacts: ArrayList<Contact>
    private lateinit var mAdapter: ContactAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.navbar)

        recycler = findViewById(R.id.contact_list)
        setupRecyclerView()
        initDataFromFile()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.more){
            val intent = Intent(this,SettingsActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    private fun setupRecyclerView() {
        mContacts = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = ContactAdapter(mContacts, this) { contact ->
            contactOnClick(contact)
        }

        recycler.adapter = mAdapter
    }

    private fun contactOnClick(contact: Contact?) {
        Log.d(TAG, "Click on this: $contact")
        contact?.let {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(contact: Contact) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(KEY_NAME, contact.name)
            putExtra(KEY_DESCRIPTION, contact.description)
            putExtra(KEY_POINTS, contact.points)
            putExtra(KEY_FOTO, contact.image)
        }
        startActivity(intent)
    }

    private fun navigateToPreferences(){

    }

    private fun initDataFromFile() {
        val contactsString = readContactJsonFile()
        try {
            val contactsJson = JSONArray(contactsString)
            for (i in 0 until contactsJson.length()) {

                val contactJson = contactsJson.getJSONObject(i)

                val contact = Contact(
                    contactJson.getString("name"),
                    contactJson.getString("description"),
                    contactJson.getString("points"),
                    contactJson.getString("image")
                )
                Log.d(TAG, "generateContacts: $contact")
                mContacts.add(contact)
            }

            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * Reads a file from the assets directory and returns it as a string.
     *
     * @return The resulting string.
     */
    private fun readContactJsonFile(): String? {
        var contactsString: String? = null
        try {
            val inputStream = assets.open("mock_contacts.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            contactsString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return contactsString
    }

    companion object {
        private val TAG = ListActivity::class.java.simpleName
        const val KEY_NAME = "contact_extra_name"
        const val KEY_DESCRIPTION = "contact_extra_description"
        const val KEY_POINTS = "contact_points"
        const val KEY_FOTO = "contact_foto"
    }
}