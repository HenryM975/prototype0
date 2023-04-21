package com.example.prototype0

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.prototype0.db.DB0
import com.example.prototype0.model.DataModel
import java.util.ArrayList

class DB0ViewModel: ViewModel() {

    val liveData = MutableLiveData<String>()

    init {
        //val db0 = DB0.getDB0()//

    }

    fun myData(db0: DB0): ArrayList<DataModel> { //RecyclerView
        var dataList = ArrayList<DataModel>()
        //var i = 0
        //while(i < 10){
        //    dataList.add(DataModel(i.toString(), "test", "test"))
        //    i++
        //}//так - ок
        db0.getDao().GetAll().asLiveData().observe(this){ list->  //list == it
            //binding.textDB.text = ""
            list.forEach {
                dataList.add(DataModel(it.id.toString(), it.Column0.toString(), it.Column1.toString()))
                //Toast.makeText(this, it.id.toString(), Toast.LENGTH_SHORT).show()//
                //dataList.add(DataModel("test", "test", "test"))
            }
        }
        return dataList
    }


}