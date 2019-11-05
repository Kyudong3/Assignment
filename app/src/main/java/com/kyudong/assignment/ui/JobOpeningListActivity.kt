package com.kyudong.assignment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.kyudong.assignment.R
import com.kyudong.assignment.data.Company
import com.kyudong.assignment.ui.adapter.JobOpeningListAdapter
import kotlinx.android.synthetic.main.activity_job_opening_list.*
import org.json.JSONArray
import java.nio.charset.Charset


class JobOpeningListActivity : AppCompatActivity() {

    private lateinit var jobListRvAdapter: JobOpeningListAdapter
    private lateinit var companyList: ArrayList<Company>
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_opening_list)

        init()

        val jsonString = getJsonData()
        parseJson(jsonString)

        setAdapter(companyList)
    }

    private fun init() {
        companyList = arrayListOf()
        gson = Gson()
    }

    private fun getJsonData() : String {
        var json: String? = null
        try {
            val inputStream = assets.open("data.json")
            val fileSize = inputStream.available()

            val buffer = ByteArray(fileSize)
            inputStream.read(buffer)
            inputStream.close()

            json = String(buffer, Charset.forName("UTF-8"))

            return json
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return json!!
    }

    private fun parseJson(jsonString: String) {
        val jsonArray = JSONArray(jsonString)

        var index = 0

        while (index < jsonArray.length()) {
            val company = gson.fromJson(jsonArray.get(index).toString(), Company::class.java)
            companyList.add(company)

            index++
        }
    }

    private fun setAdapter(list: ArrayList<Company>) {
        jobListRvAdapter = JobOpeningListAdapter(list, this@JobOpeningListActivity)

        val divider = setDivider()

        list_job_rv.apply {
            addItemDecoration(divider)
            layoutManager = LinearLayoutManager(this@JobOpeningListActivity)
            adapter = jobListRvAdapter
        }
    }

    private fun setDivider(): DividerItemDecoration {
        val divider = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider_recyclerview)!!)

        return divider
    }
}
