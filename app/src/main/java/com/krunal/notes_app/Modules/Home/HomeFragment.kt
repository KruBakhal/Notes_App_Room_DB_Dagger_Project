package com.krunal.notes_app.Modules.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.krunal.notes_app.Adapter.HomeNotesAdapter
import com.krunal.notes_app.Database.NoteDAO
import com.krunal.notes_app.Intermediate.BaseInterface
import com.krunal.notes_app.MyApplication
import com.krunal.notes_app.R
import com.krunal.notes_app.databinding.FragmentHomeBinding
import com.krunal.notes_app.DI.AppComponent
import com.krunal.notes_app.Model.NotesModel
import com.krunal.notes_app.Modules.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment() {
    private lateinit var adapter: HomeNotesAdapter
    private lateinit var notes: List<NotesModel>
    private lateinit var viewBinding: FragmentHomeBinding
    private lateinit var appComponent: AppComponent

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
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentHomeBinding.inflate(layoutInflater)
        val viewBinding = viewBinding.root
        return viewBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        (requireContext().applicationContext as MyApplication).getAppComponent().inject(this)

        adapter = HomeNotesAdapter(ArrayList(), object : BaseInterface {
            override fun onCLickItems(position: Int) {
                var bundle: Bundle = Bundle()
                bundle.putString(ARG_PARAM1, Gson().toJson(notes[position]))
                findNavController().navigate(
                    R.id.action_homeFragment_to_editNotesFragment,
                    bundle
                )
            }
        })
        viewBinding.rvHome.adapter = adapter

        viewBinding.floatingBar.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addNotesFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        launch {
            context?.let {
                viewBinding.pgHome.visibility = View.VISIBLE
                notes = userDao.getAllNotes()// AppDatabase(it).userDao().getAllNotes()
//                if (adapter != null)
                adapter.setListData(notes)
                viewBinding.pgHome.visibility = View.GONE
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}