package com.example.task

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.*

class one_Fragment : Fragment() {

    private lateinit var task1_recycler_view: RecyclerView
    private var callbacks: Callbacks? = null

    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

    private val taskListViewModel: ListViewModel by lazy {
        ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

    interface Callbacks {
        fun onCrimeSelected(taskId: UUID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    private fun updateUI(taks: List<Task>) {

            adapter = CrimeAdapter(taks)
        task1_recycler_view.adapter = adapter
            val adapt= task1_recycler_view.adapter as CrimeAdapter
            adapt.submitList(taks)}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.one_fragment, container, false)
        task1_recycler_view = view.findViewById(R.id.task1_recycler_view) as RecyclerView
        task1_recycler_view.layoutManager = LinearLayoutManager(context)
        task1_recycler_view.adapter = adapter
        //  updateUI()
        return view
    }


    companion object {
        fun newInstance(s: String, s1: String): one_Fragment {
            return one_Fragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListViewModel.taskListLiveData.observe(
            viewLifecycleOwner,
            Observer { task ->
                task?.let {
                    Log.i(ContentValues.TAG, "Got crimes ${task.size}")
                    // updateUI(trimes)

                }
            })


    }

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var task: Task
        val titleTextView: TextView = itemView.findViewById(R.id.Task_Title)
        val detailsTextView: TextView = itemView.findViewById(R.id.Task_details)
        val dateTextView: TextView = itemView.findViewById(R.id.Task_date)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(task: Task) {

            this.task = task
            titleTextView.text = this.task.title
            dateTextView.text = DateFormat.getDateInstance(DateFormat.LONG)
                    .format(this.task.date).toString()

            detailsTextView.text = this.task.details


        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }

    }

    private inner class CrimeAdapter(var tasks: List<Task>) : ListAdapter<Task,CrimeHolder>(TaskDiffutil()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            //  val toast = Toast.makeText(context,(crimes.size).toString() , Toast.LENGTH_LONG)
            //toast.show()

            val  view= layoutInflater.inflate(R.layout.task_list_fragment, parent, false)
            return CrimeHolder(view)
        }
        override fun getItemCount() = tasks.size
        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val cr =tasks[position]

            holder.bind(cr)
        }
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

}