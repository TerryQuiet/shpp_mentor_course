package tk.quietdev.level1.ui.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import tk.quietdev.level1.databinding.FragmentSettingsBinding
import tk.quietdev.level1.databinding.UserDetailBinding
import tk.quietdev.level1.models.User
import tk.quietdev.level1.utils.Const
import tk.quietdev.level1.utils.ext.loadImage

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var userDetailBinding: UserDetailBinding
    private val viewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentSettingsBinding.inflate(inflater, container, false).apply {
            binding = this
            userDetailBinding = binding.topContainer

        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = activity?.intent?.getParcelableExtra<User>(Const.EMAIL)
        viewModel.currentUser = user!!
        bindListeners()
        bindValues()
    }

    private fun bindListeners() {
        binding.apply {
            btnViewContacts.setOnClickListener {

            }
            btnEditProfile.setOnClickListener {

            }
        }
    }

    private fun bindValues() {
        val currentUser = viewModel.currentUser
        binding.topContainer.apply {
            tvName.text = currentUser.userName
            tvAddress.text = currentUser.physicalAddress
            tvOccupation.text = currentUser.occupation
            ivProfilePic.loadImage(currentUser.pictureUri)
        }
    }

    /*private fun openContacts() {
       val intent = Intent(this, ContactsActivity::class.java)
        startActivity(intent)
        finish()
    }*/
}