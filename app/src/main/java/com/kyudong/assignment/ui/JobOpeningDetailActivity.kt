package com.kyudong.assignment.ui

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.kyudong.assignment.R
import com.kyudong.assignment.data.Company
import com.kyudong.assignment.util.DateFormat
import kotlinx.android.synthetic.main.activity_job_opening_detail.*
import kotlinx.android.synthetic.main.company_detail_toolbar.*
import java.util.*


class JobOpeningDetailActivity : AppCompatActivity() {
    lateinit var company: Company

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_opening_detail)

        if (intent.extras != null) {
            company = intent.getSerializableExtra("company") as Company
        }

        setToolbar()
        setCount()
        setDate(company.end_time)
        setCompanyFields(company.fields)
        setContent(company.content)
    }

    private fun setToolbar() {
        company_detail_toolbar_name_txv.text = company.name
        company_detail_toolbar_back_imv.setOnClickListener {
            finish()
        }
    }

    private fun setCount() {
        detail_view_count_txv.text = " 조회 " + company.view_count.toString()
        detail_favorite_count_txv.text = " " + company.favorite_count.toString()
        detail_resume_count_txv.text = " 작성 " + company.resume_count.toString()
    }

    private fun setDate(end_time: String) {
        val date = DateFormat().getDateFromString(end_time)
        val month = DateFormat().getMonth(date)
        val day = DateFormat().getDay(date)
        val hour = DateFormat().getHour(date)
        val min = DateFormat().getMin(date)

        val currentDate = DateFormat().getCurrentDate()
        val currentDay = DateFormat().getDay(currentDate)

        detail_end_time_txv.text = """~${month}월 ${day}일 ${hour}시 ${min}분("""

        val sub = day.toInt() - currentDay.toInt()

        if (sub < 0) {
            detail_remain_time_txv.text = "마감"
        } else {
            detail_remain_time_txv.text = "${sub}일 남음"
        }
    }

    private fun setCompanyFields(fields: ArrayList<String>) {
        val sb = StringBuilder()

        for (field in fields) {
            if (fields.size > 5) {
                if (fields.indexOf(field) < 4) sb.append("$field,\n")
                else if (fields.indexOf(field) == 4) sb.append("$field...")
            } else {
                if (fields.last() == field) sb.append(field)
                else sb.append("$field,\n")
            }

            detail_field_txv.text = sb.toString()
        }
    }

    private fun setContent(content: String) {
        val contentString = "<html><body>$content</body></html>"

        detail_content_webView.settings.apply {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            useWideViewPort = true
            loadWithOverviewMode = true
            displayZoomControls = false
        }

        detail_content_webView.webViewClient = WebViewClient()
        detail_content_webView.loadData(contentString, "text/html", "UTF-8")
    }
}
