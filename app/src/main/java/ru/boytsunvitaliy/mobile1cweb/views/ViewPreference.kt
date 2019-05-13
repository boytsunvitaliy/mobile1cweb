package ru.boytsunvitaliy.mobile1cweb.views

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.boytsunvitaliy.mobile1cweb.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.R.id
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior


abstract class ViewPreference : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_preference, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager
            .beginTransaction()
            .replace(R.id.settings, getPreference(), "ViewPreference")
            .commit()
    }

    override fun onStart() {
        super.onStart()
        val bottomSheet = dialog as? BottomSheetDialog
        bottomSheet?.behavior?.state =BottomSheetBehavior.STATE_EXPANDED
        bottomSheet?.behavior?.peekHeight = 0
    }

    protected abstract fun getPreference() : PreferenceFragmentCompat
}