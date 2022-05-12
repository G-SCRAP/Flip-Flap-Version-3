package com.example.flipflapp.ui.drawer
//Gavin Ogren
//May 11th 2022
// Flip Flapp is a Android Application that was implemented to improve studying.
// It was written in Kotlin using Android Studio.
import android.content.Context
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flipflapp.R
import com.example.flipflapp.databinding.FragmentSignInBinding
import com.example.flipflapp.sql.sets.SetModel
import com.example.flipflapp.sql.sets.SqliteHelperSets
import org.w3c.dom.Text

class HomeFragment : Fragment() {
    private var setList: ArrayList<SetModel> = ArrayList()

    private var _binding: FragmentSignInBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //The view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Runs the setHasOptionMenu in the fragment class that just tells a fragment it has a
        //option menu
        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //Creates a Options Menu and inflates it to the fragments super OnCreate Class
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_menu, menu)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
       return when (item.itemId){
           R.id.action_homeFragment_to_editSetsFragment ->true
           else -> super.onOptionsItemSelected(item)
       }
    }
}

// Sets RecyclerView
class SetAdapter(
    private val context: Context,
    private val dataset: List<SetModel>
    ): RecyclerView.Adapter<SetAdapter.SetViewHolder>() {

    //Provides a reference to the view for each data set complex data
    //may need more than one view per item.
    class SetViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.item_title)
    }

    // Creates new view (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_sets,parent, false)
        return SetViewHolder(adapterLayout)
    }

    //Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: SetViewHolder, position: Int) {
        val set = dataset[position]
        holder.textView.text = "none"
    }

    //returns the size of your dataset (Invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataset.size
    }
}
