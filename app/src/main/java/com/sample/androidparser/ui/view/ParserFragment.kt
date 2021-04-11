package com.sample.androidparser.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.androidparser.R
import com.sample.androidparser.data.api.ApiHelper
import com.sample.androidparser.data.api.RetrofitBuilder
import com.sample.androidparser.data.model.Parser
import com.sample.androidparser.ui.adapter.DataAdapter
import com.sample.androidparser.ui.viewmodel.DataViewModel
import com.sample.androidparser.ui.viewmodel.ViewModelFactory
import com.sample.androidparser.utils.Status
import kotlinx.android.synthetic.main.fragment_parser.*

class ParserFragment : Fragment(), DataAdapter.OnItemClickListener {

    private lateinit var viewModel: DataViewModel
    private var adapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI()
        setDataObservers()
    }

    private fun setViewModel() {
        viewModel =
            ViewModelProviders.of(
                this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
            ).get(DataViewModel::class.java)
    }

    private fun updateUI() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = DataAdapter(arrayListOf())
        adapter?.setOnSearchItemClickListener(this)
        recyclerView.adapter = adapter
    }

    private fun setDataObservers() {
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }

                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { parser -> retrieveList(parser) }
                    }

                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun retrieveList(parserList: List<Parser>) {
        adapter?.apply {
            addData(parserList)
            notifyDataSetChanged()
        }
    }

    override fun onItemClick(v: View, parser: Parser?) {
        val action = ParserFragmentDirections.actionNavParserToNavCriteria(parser)
        v.findNavController().navigate(action)
    }
}