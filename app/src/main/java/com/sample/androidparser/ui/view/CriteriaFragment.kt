package com.sample.androidparser.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.androidparser.R
import com.sample.androidparser.data.model.Parser
import kotlinx.android.synthetic.main.fragment_criteria.*
import java.text.DecimalFormat

class CriteriaFragment : Fragment() {

    private lateinit var parser: Parser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val args = CriteriaFragmentArgs.fromBundle(requireArguments())
            parser = args.parserDetails!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_criteria, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI()
    }

    private fun updateUI() {
        val regex = Regex("\\$\\d+")
        val criteriaList = ArrayList<String>()

        for (item in parser.criteria) {
            var criteria = item.text

            if (!item.variable.isNullOrEmpty()) {
                val format = DecimalFormat("0.#")

                criteria = criteria.replace(regex) {
                    val obj = item.variable[it.value]
                    when (obj?.type) {
                        "indicator" -> "(${obj.defaultValue})"
                        "value" -> {
                            val value = format.format(obj.values[0])
                            "($value)"
                        }
                        else -> ""
                    }
                }
            }
            criteriaList.add(criteria)
        }

        val formattedCriteria = criteriaList.reduce { acc, s ->
            "$acc\n\nand \n\n$s"
        }

        tv_criteria.text = formattedCriteria
    }
}