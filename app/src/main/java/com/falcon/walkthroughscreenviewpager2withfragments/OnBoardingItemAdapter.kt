package com.falcon.walkthroughscreenviewpager2withfragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnBoardingItemAdapter(private val onBoardingItems: List<OnBoardingItem>): RecyclerView.Adapter<OnBoardingItemAdapter.OnboardingItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.onboarding_item_container,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItems[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItems.size
    }

    inner class OnboardingItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageOnBoarding = view.findViewById<ImageView>(R.id.imageOnBoarding)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)

        fun bind(onBoardingItems: OnBoardingItem) {
            imageOnBoarding.setImageResource(onBoardingItems.onBoardingImage)
            textTitle.text = onBoardingItems.title
            textDescription.text = onBoardingItems.description
        }
    }
}