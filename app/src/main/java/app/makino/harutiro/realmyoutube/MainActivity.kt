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

            //出力部分
            for(person in persons) {
                 out += ("name = " + person.name + "　" + "age = " + person.age + "\n")

            }

            println("===============================")
            println(out)



        }

        //satouを探す
        readS.setOnClickListener {
            //realmのインスタンス
            val realm:Realm = Realm.getDefaultInstance()

            //realm.where(DBのクラス::class.java).findAll().sort(フィールド名)
            //.findAll()は全検索。
            //.sort(フィールド名)はソート。
            val persons: RealmResults<DateClass> = realm.where(DateClass::class.java).equalTo("name","satou").findAll()

            var out = ""



            //出力部分
            for(person in persons) {
                out += ("name = " + person.name + "　" + "age = " + person.age + "\n")

            }

            println("===============================")
            println(out)

        }

        //名前に＊をつける
        update.setOnClickListener {
            //realmのインスタンス
            val realm:Realm = Realm.getDefaultInstance()

            //realm.where(DBのクラス::class.java).findAll().sort(フィールド名)
            //.findAll()は全検索。
            //.sort(フィールド名)はソート。
            val persons: RealmResults<DateClass> = realm.where(DateClass::class.java).findAll()

            realm.executeTransaction{
                for (person in persons){
                    person.name += "*"
                }

            }
            println("===============================")
            println("更新完了")



        }

        //全部アスタリスクにしちゃう
        updateAll.setOnClickListener{
            //realmのインスタンス
            val realm:Realm = Realm.getDefaultInstance()

            //realm.where(DBのクラス::class.java).findAll().sort(フィールド名)
            //.findAll()は全検索。
            //.sort(フィールド名)はソート。
            val persons: RealmResults<DateClass> = realm.where(DateClass::class.java).findAll()

            realm.executeTransaction{
                for (person in persons){
                    person.name = "*******"
                }

            }
            println("===============================")
            println("更新完了")

        }

        //satouを消す
        delete.setOnClickListener {
            //realmのインスタンス
            val realm:Realm = Realm.getDefaultInstance()

            //realm.where(DBのクラス::class.java).findAll().sort(フィールド名)
            //.findAll()は全検索。
            //.sort(フィールド名)はソート。
            val persons: RealmResults<DateClass> = realm.where(DateClass::class.java).equalTo("name","satou").findAll()


            //読み込んだデータを一時配列に入れる
            var personArray = mutableListOf<DateClass>()
            for(person in persons){
                personArray.add(person)
            }

            //配列に入ったデータを消す
            realm.executeTransaction {
                for (person in persons){
                    person.deleteFromRealm()
                }
            }

            println("===============================")
            println("satou消去完了")


        }

        //全部消す
        deleteAll.setOnClickListener {
            //realmのインスタンス
            val realm:Realm = Realm.getDefaultInstance()

            //realm.where(DBのクラス::class.java).findAll().sort(フィールド名)
            //.findAll()は全検索。
            //.sort(フィールド名)はソート。
            val persons: RealmResults<DateClass> = realm.where(DateClass::class.java).findAll()

            //全データ消す
            realm.executeTransaction {
                realm.deleteAll()
            }
            println("===============================")
            println("消去完了")

        }

        //初期データ作成
        init.setOnClickListener {
            //realmのインスタンス
            val realm:Realm = Realm.getDefaultInstance()

            //realm.where(DBのクラス::class.java).findAll().sort(フィールド名)
            //.findAll()は全検索。
            //.sort(フィールド名)はソート。
            val persons: RealmResults<DateClass> = realm.where(DateClass::class.java).findAll()

            realm.executeTransaction{
                realm.deleteAll()

                val new1:DateClass = it.createObject(DateClass::class.java)
                new1.name = "satou"
                new1.age = "20"

                val new2:DateClass = it.createObject(DateClass::class.java)
                new2.name = "takahasi"
                new2.age = "34"

                val new3:DateClass = it.createObject(DateClass::class.java)
                new3.name = "oota"
                new3.age = "12"

            }


            println("===============================")
            println("追加完了")

        }








    }

}