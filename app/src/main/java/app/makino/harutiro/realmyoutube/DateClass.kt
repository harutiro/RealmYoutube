package app.makino.harutiro.realmyoutube

import io.realm.RealmObject

open class DateClass (//メモのクラスを定義 //openを書くのはRealmを使う際に必要
    //保存するデータの要素となる変数を定義する

    open var name:String = "",
    open var age:Int = 0

        ):RealmObject()//RealmObjectという方を継承している部分 メモというクラスをRealmで保存できる型にすることができる