package com.example.isp111english.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.isp111english.R
import com.example.isp111english.data.model.Question
import com.example.isp111english.databinding.QuestionItemBinding

class QuestionsAdapter(val context: Context, val list: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionsAdapter.QuestionsHolder>() {

    class QuestionsHolder(questItem: View) : RecyclerView.ViewHolder(questItem) {
        val binding = QuestionItemBinding.bind(questItem)
        fun bindQuestionItemViewToQuestionItemData(question: Question) = with(binding) {
            var question_splitted = question.text.split("___")
            if (question_splitted.size < 2) {
                throw IllegalArgumentException("В вопросе не содержится знак подстановки ответа ___")
            }
            part1.text = question_splitted[0]
            part2.text = question_splitted[1]
            rutitle.text = question.rutext
        }

        // val part1:TextView = questItem.findViewById(R.id.part1)
        // val part2:TextView = questItem.findViewById(R.id.part2)
        // val rutitle:TextView = questItem.findViewById(R.id.rutitle)

        val answerField: EditText = questItem.findViewById(R.id.answer)
//        val answer: String
//            get() = answerField.text.toString()
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsHolder {
        val newQuestItemView =
            LayoutInflater.from(context).inflate(R.layout.question_item, parent, false)
        return QuestionsHolder(newQuestItemView)
    }

    override fun onBindViewHolder(questionHolder: QuestionsHolder, position: Int) {
        /// questionHolder.frame_img.setImageResource(list[position].imageId)
        questionHolder.bindQuestionItemViewToQuestionItemData(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }


}