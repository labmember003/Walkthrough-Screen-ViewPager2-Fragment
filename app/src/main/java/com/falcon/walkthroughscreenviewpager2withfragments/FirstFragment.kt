package com.falcon.walkthroughscreenviewpager2withfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.falcon.walkthroughscreenviewpager2withfragments.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var onBoardingItemAdapter: OnBoardingItemAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        setOnboardingItem()
        setUpIndicators()
        setCurrentIndicator(0)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setOnboardingItem() {
        onBoardingItemAdapter = OnBoardingItemAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.drawable.google,
                    title = "First",
                    description = "description1"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.girl,
                    title = "Second",
                    description = "description2"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.ic_goole,
                    title = "Third",
                    description = "description3"
                )
            )
        )
        binding.onBoardingViewPager.adapter = onBoardingItemAdapter
        binding.onBoardingViewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (binding.onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.imageNext.setOnClickListener {
            if (binding.onBoardingViewPager.currentItem + 1 < onBoardingItemAdapter.itemCount) {
                binding.onBoardingViewPager.currentItem += 1
            } else {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
        binding.textSkip.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonGetStarted.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun setUpIndicators() {
        indicatorsContainer = binding.indicatorsContainer
        val indicators = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(requireContext().applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext().applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext().applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext().applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}