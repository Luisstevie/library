package com.color.sms.messages.theme.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.color.sms.messages.theme.BuildConfig
import com.color.sms.messages.theme.R
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.formats.NativeAd
import java.util.*

class MediumNativeAdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mNativeBannerAd: NativeAd? = null

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
        val template = itemView.findViewById<TemplateView>(R.id.my_template)
        loadAdmob(template)
    }

    private fun loadAdmob(template: TemplateView) {
        val adLoader = AdLoader.Builder(itemView.context, nativeId)
                .forUnifiedNativeAd { template.setNativeAd(it) }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(i: Int) {
                        //                        itemView.setVisibility(View.GONE);
                    }
                })
                .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }
}
