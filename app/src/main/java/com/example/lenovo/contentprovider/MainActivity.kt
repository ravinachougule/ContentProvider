package com.example.lenovo.contentprovider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = arrayListOf<String>()

        val proj = arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        )
        val sel = null
        val slArg = null
        val srtOr = null
        val cursr = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                proj,
                sel,
                slArg,
                srtOr
        )

        while(cursr.moveToNext()) {
            val nm = cursr.getString(
                    cursr.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
            )

            val num = cursr.getString(
                    cursr.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
            )
            data.add("$nm \n $num")
            Log.i("abc","data is:$data")
        }

        listView.adapter = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_list_item_1,
                data
        )
        cursr.close()
    }
}
