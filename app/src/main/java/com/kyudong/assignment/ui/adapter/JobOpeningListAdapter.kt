package com.kyudong.assignment.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyudong.assignment.R
import com.kyudong.assignment.data.Company
import com.kyudong.assignment.extensions.start
import com.kyudong.assignment.ui.JobOpeningDetailActivity
import com.kyudong.assignment.util.DateFormat
import kotlinx.android.synthetic.main.list_job_item.view.*

class JobOpeningListAdapter(
    private val companyList: ArrayList<Company>,
    val context: Context
) : RecyclerView.Adapter<JobOpeningListAdapter.JobOpeningListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOpeningListViewHolder {
        return JobOpeningListViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.list_job_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    override fun onBindViewHolder(holder: JobOpeningListViewHolder, position: Int) {
        holder.bind(companyList[position])
    }


    class JobOpeningListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(company: Company) {
            itemView.apply {
                list_job_company_name_txv.text = company.name

                val date = DateFormat().getDateFromString(company.end_time)
                val month = DateFormat().getMonth(date)
                val day = DateFormat().getDay(date)
                val hour = DateFormat().getHour(date)
                val min = DateFormat().getMin(date)

                list_job_end_time_txv.text = """~${month}월 ${day}일 ${hour}시 ${min}분"""

                Glide.with(context).load(company.image_url).into(list_job_image_imv)

                val fieldString = StringBuffer()
                for (field in company.fields) {
                    if (field != company.fields.last()) {
                        fieldString.append("$field, ")
                    } else {
                        fieldString.append(field)
                    }
                }

                list_job_field_txv.text = fieldString.toString()

                setOnClickListener {
                    context.start(JobOpeningDetailActivity::class, company)
                }
            }
        }
    }
}