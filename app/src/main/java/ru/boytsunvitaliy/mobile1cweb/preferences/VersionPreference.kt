package ru.boytsunvitaliy.mobile1cweb.preferences

import android.content.Context
import android.util.AttributeSet
import android.util.Log

import androidx.preference.Preference
import ru.boytsunvitaliy.mobile1cweb.R

class VersionPreference : Preference {
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    init {
        title = context.getString(R.string.title_version)
        summary = getVersion()
    }

    private fun getVersion(): String {
        return try {
            val packageManager = context.packageManager
            val packageName = context.packageName
            packageManager.getPackageInfo(packageName, 0).versionName
        } catch (e:Exception){
            Log.e("VersionPreference", "getVersion", e)
            ""
        }
    }
}
