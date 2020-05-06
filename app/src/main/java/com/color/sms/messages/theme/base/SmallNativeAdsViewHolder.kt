package com.color.sms.messages.theme.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.color.sms.messages.theme.BuildConfig
import com.color.sms.messages.theme.R
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import java.util.*

class SmallNativeAdsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nativeId: String
        get() {
            return if (BuildConfig.DEBUG) {
                itemView.resources.getString(R.string.admob_native_test)
            } else {
                val random = Random()
                val randomInteger = random.nextInt(3)
                itemView.resources.getString(R.string.admob_native_1)
            }
        }

    fun bind() {
        loadAdmob()
    }

    private fun loadAdmob() {
        val adLoader = AdLoader.Builder(itemView.context, nativeId)
                .forUnifiedNativeAd { unifiedNativeAd ->
                    val template = itemView.findViewById<TemplateView>(R.id.my_template)
                    template.visibility = View.VISIBLE
                    template.setNativeAd(unifiedNativeAd)
                }
                .build()
        adLoader.loadAd(getBuildRequest())
    }

    private fun getBuildRequest(): AdRequest {
        return if (BuildConfig.DEBUG) {
            AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build()
        } else {
            AdRequest.Builder().build()
        }
    }
}
