package uz.itschool.myapplication
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.core.text.isDigitsOnly
class MainActivity : AppCompatActivity(), View.OnClickListener{    private lateinit var one:Button
    private lateinit var two: Button
    private lateinit var three:Button
    private lateinit var four:Button
    private lateinit var five:Button
    private lateinit var six:Button
    private lateinit var seven:Button
    private lateinit var eight:Button
    private lateinit var nine:Button
    private lateinit var zero:Button
    private lateinit var show_num: TextView
    private lateinit var show_answer:TextView
    private lateinit var dot:Button
    private lateinit var del:Button
    private lateinit var equal:Button
    private lateinit var clear:Button
    private lateinit var div:Button
    private lateinit var multply:Button
    private lateinit var plus:Button
    private lateinit var minus:Button
    private var isSimvol=false
    private var isPoint=true
    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initui()
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)
        dot.setOnClickListener{
            if(isPoint) {
                if (show_num.text.substring(show_num.text.length-1, show_num.text.length).isDigitsOnly()) {
                show_num.text = show_num.text.toString() + "."                }
        }
            isPoint = false
        }
        clear.setOnClickListener{
            show_num.text="0"
            isSimvol=false
            isPoint=true
        }

        del.setOnClickListener{
            if(show_num.text.length!=1) {
            show_num.text = show_num.text.substring(0, show_num.text.length - 1)            }
        else{                show_num.text = show_num.text.substring(0, show_num.text.length - 1)
            show_num.text = "0"            }
        }
        div.setOnClickListener{
            addSimvol(div.text.toString())
        }
        multply.setOnClickListener{
            addSimvol(multply.text.toString())
        }
        plus.setOnClickListener{
            addSimvol(plus.text.toString())
        }
        minus.setOnClickListener{
            addSimvol(minus.text.toString())
        }
    }

    override fun onClick(p0: View?) {
        val btn=findViewById<Button>(p0!!.id)
        if(show_num.text!="0"){
            show_num.text=show_num.text.toString()+btn.text
        }        else{
            show_num.text=btn.text        }
        show_answer.text=calculate()
        isSimvol=true

    }
    private fun calculate(): String {
        var r = "answer"
        var list = createArray(show_num.text.toString())
        math(list)

        return r
    }
    fun addSimvol(simvol:String){
        isPoint=true
        if(isSimvol){
            show_num.text=show_num.text.toString()+simvol
            isSimvol=false
        }
        else {
            if (show_num.text != "0") {
                show_num.text = show_num.text.substring(0, show_num.text.length - 1) + simvol
            }
        }
    }
    fun createArray(s:String):MutableList<Any>{
        var list = mutableListOf<Any>()
        var temp = ""
        for(i in s){
            if(i.isDigit() || i=='.'){
            temp+=i
            }
        else{
            list.add(temp.toFloat())
            list.add(i)
                temp=""
        }
        }
        if(temp.isNotEmpty()){
            list.add(temp.toFloat())
        }
        return list
    }



    fun math(list_problem:MutableList<Any>):MutableList<Any>{
        var list_new= mutableListOf<Any>()
        var temp = list_problem[0]
        for(i in list_problem.indices){
            list_new.add(list_problem[i])
            if(list_problem[i]=="*"  || list_problem[i]=="/"){
                var old:Float = list_problem[i-1] as Float
                var prev:Float = list_problem[i+1] as Float
                when(list_problem[i]){
                    '*'->{
                        temp = old*prev
                        list_new.removeLast()
                        list_new.add(temp)
                    }
                }
            }
        }
        return list_new
    }




    fun initui(){
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        dot=findViewById(R.id.dot)
        del=findViewById(R.id.del)
        equal=findViewById(R.id.equal)
        show_num=findViewById(R.id.show_num)
        show_answer=findViewById(R.id.show_answer)
        div=findViewById(R.id.div)
        multply=findViewById(R.id.multiple)
        clear=findViewById(R.id.clear_button)
        plus=findViewById(R.id.plus)
        minus=findViewById(R.id.minus)
    }}