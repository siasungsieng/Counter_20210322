package tw.edu.pu.csim.tcyang.counter_20210322

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnTouchListener {

    var counter:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txv.setOnClickListener(this)
        img.setOnClickListener(this)
        //btn4.setOnClickListener(this)
        btn4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                counter = (1..100).random()
                txv.text = counter.toString()

            }


        })
        txv.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                counter += 2
                txv.text = counter.toString()
                return false
            }

        })
        txvvb.setOnTouchListener(this)
    }




        fun Add(v: View) {
            if (v == btn1) {
                counter++
            } else if (v == btn2) {
                counter += 2
            } else {
                counter = 0
            }

            txv.text = counter.toString()
        }

        override fun onClick(v: View?) {
            if (v == txv) {
                counter++
            } else if (v == img) {
                counter += 2

            }
            /*
        else{
            counter=(1..100).random()
            txv.text = counter.toString()
        }


         */
            txv.text = counter.toString()

        }

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {

            //txvvb.text="触控测试"
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            if (event?.action == MotionEvent.ACTION_DOWN) {
                txvvb.text = "手指压下"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    //Build.VERSION.SDK_INT >= 26, New vibrate method for API Level 26 or higher
                    vibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(5000)
                }
            }

            else if (event?.action == MotionEvent.ACTION_UP){
                txvvb.text = "手指弹开"
                vibrator.cancel()
            }
            return true
        }



}