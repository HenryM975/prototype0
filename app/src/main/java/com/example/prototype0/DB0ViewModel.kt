package com.example.prototype0

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.prototype0.db.DB0
import com.example.prototype0.db.DB0Dao
import com.example.prototype0.db.DB0Entity
import com.example.prototype0.model.DataModel
import kotlinx.coroutines.flow.*
import java.util.ArrayList

class DB0ViewModel(app: Application): AndroidViewModel(app) {

    //val liveData = MutableLiveData<ArrayList<DataModel>>()
    val liveData = MutableLiveData<ArrayList<DataModel>>()
    init {
        //val db0 = DB0.getDB0()//
        val db0 = DB0.getDB0(app)
        //liveData.value = db0
        val allWords: Flow<List<DB0Entity>> = db0.getDao().GetAll()
        var dataList = ArrayList<DataModel>()
        dataList.add(DataModel("12", "13", "14"))
        var list = db0.getDao().GetAll().map{ list-> //map?
            list.forEach{
                dataList.add(DataModel(it.id.toString(), it.Column0.toString(), it.Column1.toString()))
                Toast.makeText(app, it.id.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        liveData.value = dataList






    }
    //fun add(entity: DB0Entity){
    //    db0.getDao().AddItem(item)
    //}




    //?
    /*
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
    */

}