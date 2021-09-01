package com.krunal.notes_app.Modules.Edit_Notes

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.krunal.notes_app.Database.NoteDAO
import com.krunal.notes_app.MyApplication
import com.krunal.notes_app.R
import com.krunal.notes_app.databinding.FragmentEditNotesBinding
import com.krunal.notes_app.Model.NotesModel
import com.krunal.notes_app.Modules.BaseFragment
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditNotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditNotesFragment : BaseFragment() {
    lateinit var notesModel: NotesModel
    private lateinit var viewBinding: FragmentEditNotesBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    @Inject
    lateinit var userDao: NoteDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

            val collectionType = object : TypeToken<NotesModel>() {}.type
            notesModel = Gson().fromJson<NotesModel>(param1, collectionType)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentEditNotesBinding.inflate(layoutInflater)
        val viewBinding = viewBinding.root
        return viewBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var notes = AppDatabase(requireContext())

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_editNotesFragment_to_homeFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        (requireContext().applicationContext as MyApplication).getAppComponent().inject(this)


        if (notesModel != null) {
            viewBinding.edTitile.setText(notesModel.title.toString())
            viewBinding.edText.setText(notesModel.text.toString())
        }

        viewBinding.txtDone.setOnClickListener {
            launch {
                notesModel.title = viewBinding.edTitile.text.toString()
                notesModel.text = viewBinding.edText.text.toString()
                notesModel.dateTime =
                    Calendar.getInstance().timeInMillis.toString()
                userDao.updateNote(notesModel)
                Toast.makeText(requireContext(), " Item Updated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editNotesFragment_to_homeFragment)
            }
        }

        viewBinding.imgDelete.setOnClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setMessage("Are Sure Want To Delete This Notes !")
            builder.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    launch {
                        userDao.deleteNote(notesModel)

                        Toast.makeText(requireContext(), " Item deleted", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_editNotesFragment_to_homeFragment)
                    }
                }

            })
            builder.setNegativeButton("NO", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                    dialog?.cancel()

                }

            })
            builder.setCancelable(false)
            builder.create().show()

        }
        viewBinding.imgBack.setOnClickListener {

            requireActivity().onBackPressed()

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditNotesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditNotesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}