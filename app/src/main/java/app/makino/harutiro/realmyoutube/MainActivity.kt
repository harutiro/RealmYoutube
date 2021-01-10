package app.makino.harutiro.realmyoutube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.nameId)
        val age = findViewById<EditText>(R.id.ageId)

        val init = findViewById<Button>(R.id.InitId)
        val readAll = findViewById<Button>(R.id.readAllId)
        val update = findViewById<Button>(R.id.updateId)
        val delete = findViewById<Button>(R.id.deleteID)

        val create = findViewById<Button>(R.id.createId)
        val readS = findViewById<Button>(R.id.readSId)
        val updateAll = findViewById<Button>(R.id.updateAllId)
        val deleteAll = findViewById<Button>(R.id.deleteAllId)

        val text = findViewById<TextView>(R.id.textView2)

        //データを追加する部分
        create.setOnClickListener {
            //realmインスタンス
            val realm:Realm = Realm.getDefaultInstance()
            //新規行に追加
            realm.executeTransaction{
                val new:DateClass = it.createObject(DateClass::class.java)
                new.name = name.text.toString()
                new.age = age.text.toString()
            }


        }

        //データを出力する部分
        readAll.setOnClickListener {
            //realmのインスタンス
            val realm:Realm = Realm.getDefaultInstance()

            //realm.where(DBのクラス::class.java).findAll().sort(フィールド名)
            //.findAll()は全検索。
            //.sort(フィールド名)はソート。
            val persons: RealmResults<DateClass> = realm.where(DateClass::class.java).findAll()

            var out = ""

            println("===============================")

            //出力部分
            for(person in persons) {
                 out += ("name = " + person.name + "　" + "age = " + person.age + "\n")

            }

            println(out)

        }




    }

}