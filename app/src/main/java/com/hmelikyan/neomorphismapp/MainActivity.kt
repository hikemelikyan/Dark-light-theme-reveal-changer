package com.hmelikyan.neomorphismapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatDelegate
import com.hmelikyan.neomorphismapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    companion object {
        private var mPrevBitmap: Bitmap? = null
    }

    private lateinit var mBinding: ActivityMainBinding
    private val shared: SharedPreferencesUtil by lazy { getSharedPreferences() }
    private var isDark: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        isDark = shared.getBooleanSharedPreferences("isDarkEnabled")!!
        initView()
    }

    private fun initView() {
        mBinding.placeHolderImage.setImageBitmap(mPrevBitmap)

        mBinding.title.text = if (isDark) "Dark mode" else "Light mode"
        mBinding.themeSwitcher.text = if (isDark) "Switch theme to light mode" else "Switch theme to dark mode"

        if (!isDark) setLightStatusBar()

        mBinding.parentLayout.post {
            startReveal(mBinding.parentLayout)
        }
    }

    override fun onStart() {
        super.onStart()
        mBinding.themeSwitcher.setOnClickListener {
            createScreenShoot()
            if (shared.getBooleanSharedPreferences("isDarkEnabled") != null && !shared.getBooleanSharedPreferences("isDarkEnabled")!!) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                shared.setBooleanSharedPreferences("isDarkEnabled", true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                shared.setBooleanSharedPreferences("isDarkEnabled", false)
            }
        }
    }

    private fun createScreenShoot() {
        val bitmap = Bitmap.createBitmap(mBinding.root.measuredWidth, mBinding.root.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        mBinding.parentLayout.draw(canvas)
        mPrevBitmap = bitmap
        mBinding.placeHolderImage.setImageBitmap(bitmap)
    }

    private fun startReveal(view: View) {
        val anim = ViewAnimationUtils.createCircularReveal(
            view,
            view.measuredWidth / 2,
            view.measuredHeight / 2,
            0f,
            view.width.coerceAtLeast(view.height) * 1.2f
        )
        anim.duration = 800
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.start()
    }
}
