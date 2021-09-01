package com.krunal.notes_app.modules.Add_Notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.krunal.notes_app.Database.NoteDAO
import com.krunal.notes_app.MyApplication
import com.krunal.notes_app.R
import com.krunal.notes_app.databinding.FragmentAddNotesBinding
import com.krunal.notes_app.model.NotesModel
import com.krunal.notes_app.modules.BaseFragment
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNotesFragment : BaseFragment() {
    private lateinit var viewBinding: FragmentAddNotesBinding
    @Inject
    lateinit var userDao: NoteDAO

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentAddNotesBinding.inflate(layoutInflater)
        val viewBinding = viewBinding.root
        return viewBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_addNotesFragment_to_homeFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        (requireContext().applicationContext as MyApplication).getAppComponent().inject(this)

        viewBinding.imgBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        viewBinding.txtDone.setOnClickListener {
launch {

                userDao.addNote(
                    NotesModel(
                        viewBinding.edTitile.text.toString(),
                        viewBinding.edText.text.toString(),
                        null,
                        null,
                        Calendar.getInstance().timeInMillis.toString()
                    )
                )
                findNavController().navigate(R.id.action_addNotesFragment_to_homeFragment)
            }
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddNotesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddNotesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}