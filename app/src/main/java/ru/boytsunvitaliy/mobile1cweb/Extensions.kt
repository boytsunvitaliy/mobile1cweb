package ru.boytsunvitaliy.mobile1cweb

import android.content.DialogInterface
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

//fun Fragment.getInteger(@IntegerRes id: Int): Int {
//    return resources.getInteger(id)
//}

//inline fun <reified TDialog : DialogFragment> Fragment.showDialog(@IntegerRes requestCodeId: Int) {
//    val dialog = TDialog::class.java.newInstance()
//    dialog.setTargetFragment(this, getInteger(requestCodeId))
//    dialog.show(requireFragmentManager(), getString(R.string.tag_dialog))
//}

//fun Fragment.setSupportActionBar(toolbar: Toolbar) {
//    (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
//}

//fun Fragment.setSupportActionBar(bottomAppBar: BottomAppBar?, toolbar: Toolbar?) {
//    setSupportActionBar(bottomAppBar)
//    setSupportActionBar(toolbar)
//}

//fun Fragment.setSupportActionBar(bottomAppBar: BottomAppBar?) {
//    val activity = requireActivity() as? AppCompatActivity
//    activity?.setSupportActionBar(bottomAppBar)
//}

fun Fragment.setSupportActionBar(toolbar: Toolbar?) {
    val activity = requireActivity() as? AppCompatActivity
    activity?.setSupportActionBar(toolbar)
}

//fun Fragment.setSupportActionBar(toolbar: Toolbar?) {
//    toolbar?.title = requireActivity().title
//}

fun Fragment.setupBackPressed(callback: OnBackPressedCallback) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}

//fun Fragment.configurationBottomAppBar(bottomAppBar: BottomAppBar, toolbar: Toolbar) {
//    setSupportActionBar(toolbar)
//    configurationBottomAppBar(bottomAppBar)
//}

//fun Fragment.configurationBottomAppBar(bottomAppBar: BottomAppBar) {
//    setSupportActionBar(bottomAppBar)
//    val navController = findNavController()
//    val appBarConfiguration = AppBarConfiguration(navController.graph)
//    bottomAppBar.setupWithNavController(navController, appBarConfiguration)
//}

//fun Fragment.setupSearch(menu: Menu) {
//    if (this !is SearchView.OnQueryTextListener) return
//    val searchView = menu.findItem(R.id.search)?.actionView as? SearchView ?: return
//    val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//    searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
//    searchView.setOnQueryTextListener(this)
//}

fun Fragment.showError(exception: Exception) {
    showError(exception.message ?: "")
}

fun Fragment.showError(throwable: Throwable) {
    showError(throwable.message ?: "")
}

fun Fragment.showError(message: String) {
    MaterialAlertDialogBuilder(context)
        .setTitle(R.string.title_error)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton("Ok", null)
        .show()
}

fun Fragment.showMessage(message: String) {
    MaterialAlertDialogBuilder(context)
        .setTitle(R.string.title_message)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton("Ok", null)
        .show()
}

fun Fragment.showMessageDialog(message: String, listener : DialogInterface.OnClickListener) {
    MaterialAlertDialogBuilder(context)
        .setTitle(R.string.title_message)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(android.R.string.ok, listener)
        .setNegativeButton(android.R.string.cancel, listener)
        .show()
}

fun Fragment.showMessageDialog(@StringRes messageId: Int, listener : DialogInterface.OnClickListener) {
    MaterialAlertDialogBuilder(context)
        .setTitle(R.string.title_message)
        .setMessage(messageId)
        .setCancelable(false)
        .setPositiveButton(android.R.string.ok, listener)
        .setNegativeButton(android.R.string.cancel, listener)
        .show()
}

fun Fragment.closeAppDioalog(){
    showMessageDialog(R.string.question_exit_app,
        DialogInterface.OnClickListener { _, which ->
            if (which == DialogInterface.BUTTON_POSITIVE)
                requireActivity().finishAffinity()
        })
}