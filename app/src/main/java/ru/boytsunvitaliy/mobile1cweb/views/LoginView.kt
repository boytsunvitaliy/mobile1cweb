package ru.boytsunvitaliy.mobile1cweb.views


import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_view.*
import ru.boytsunvitaliy.mobile1cweb.R
import ru.boytsunvitaliy.mobile1cweb.closeAppDioalog
import ru.boytsunvitaliy.mobile1cweb.models.AuthenticationState
import ru.boytsunvitaliy.mobile1cweb.setSupportActionBar
import ru.boytsunvitaliy.mobile1cweb.viewmodels.MainViewModel

class LoginView : BaseView() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(toolbar)
        val navController = findNavController()
        buttonSet.setOnClickListener {
            viewModel.authenticate(codeView.text.toString())
        }
        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                AuthenticationState.INVALID_AUTHENTICATION ->
                    Snackbar.make(
                        view,
                        R.string.invalid_credentials,
                        Snackbar.LENGTH_SHORT
                    ).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.login_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_settings -> {
                val dialog = LoginViewPreference()
                dialog.show(requireFragmentManager(), null)
                true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onBackPressed() {
        this@LoginView.closeAppDioalog()
    }
}

class LoginViewPreference : ViewPreference() {
    override fun getPreference(): PreferenceFragmentCompat {
        return LoginPreference()
    }
}
class LoginPreference : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}