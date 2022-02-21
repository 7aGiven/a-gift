package buzz.giiiiiv.gift

import android.accessibilityservice.AccessibilityService
import android.graphics.Color
import android.text.*
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread

class MyAccessibilityService : AccessibilityService() {
    lateinit var popup:Toast
    val client= OkHttpClient()

    override fun onCreate() {
        super.onCreate()
        popup= Toast.makeText(this,"",Toast.LENGTH_LONG)
    }

    override fun onInterrupt() {}

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if(event?.eventType==AccessibilityEvent.TYPE_VIEW_CLICKED){
            popup.cancel()
            popup=Toast.makeText(this,"",Toast.LENGTH_LONG)
            System.out.println("单击")
        }else{
            System.out.println("长按")
            try {
//                recycle(rootInActiveWindow,"")
                val node=rootInActiveWindow
                    .getChild(0)
                    .getChild(2)
                    .getChild(0)
                    .getChild(0)
                    .getChild(1)
                    .getChild(0)
                    .getChild(0)
                var i=1
                var question=node.getChild(0).getChild(2).text.toString()
                while (i<node.childCount){
                    val child=node.getChild(i).getChild(1)
                    if (child!=null){
                        question+=child.text
                    }
                    i++
                }
                println(question)
                http(question)
            }catch (e: Exception){
                popup.setText(e.toString())
                popup.show()
            }
        }
    }
//    fun recycle(e:AccessibilityNodeInfo,str:String){
//        println(str+e)
//        var n=0
//        while (n<e.childCount){
//            recycle(e.getChild(n),str+n+",")
//            n++
//        }
//    }
    fun http(q:String){
        val reqbody=Req.Pb.newBuilder()
            .setA(3)
            .setB(q)
            .setC("")
            .setD(0)
            .setE(0)
            .setF(1)
            .setG("2269856547474494-1636463830589")
            .build()
            .toByteArray()
            .toRequestBody()
        val req=okhttp3.Request.Builder()
            .url("https://xxy.51xuexiaoyi.com/el/v0/sou/search?iid=3633235494969854&device_id=3809156189927959&ac=wifi&channel=huawei_199563&aid=199563&app_name=xxy&version_code=10300&version_name=1.3.0&device_platform=android&os=android&ssmix=a&device_type=CDY-AN00&device_brand=google&language=zh&os_api=25&os_version=7.1.1&openudid=66e117c8d0b76a74&manifest_version_code=10300&resolution=1080*2148&dpi=480&update_version_code=1030005&_rticket=1636721246602&cdid=1f6f70e6-2701-4320-a90a-ed4843222dad&uuid=3809156189927959&el_app_version=10300")
            .addHeader("Cookie","passport_csrf_token_default=fdcc9d6ed72bbc27474be582ce739a9e;passport_csrf_token=fdcc9d6ed72bbc27474be582ce739a9e;multi_sids=2269856547474494%3A01be99977e2da6c7de0c82114bc9db07;odin_tt=bda9b4131ff4248f7be920a1721665bedfd9ada5f4aa5f4051c24782c410e91fef5e4203d5a28afd4ebccb20cb1ba7a45b5d582e56eaef14958659c6aaa7a1477f453da706b6a69ea598ada6218d2779;n_mh=QpQ3CrR_wP9DJ-zBCDJ4hmozknmtUV5Kddr_LeN3HkQ;sid_guard=01be99977e2da6c7de0c82114bc9db07%7C1645449354%7C5183999%7CFri%2C+22-Apr-2022+13%3A15%3A53+GMT;uid_tt=1d8e1197c335a399aac3ad904105e9d5;uid_tt_ss=1d8e1197c335a399aac3ad904105e9d5;sid_tt=01be99977e2da6c7de0c82114bc9db07;sessionid=01be99977e2da6c7de0c82114bc9db07;sessionid_ss=01be99977e2da6c7de0c82114bc9db07;d_ticket=9eeffb864ed2e4c3e3508f0dc12ed09cfa413;")
            .addHeader("X-Vc-Bdturing-Sdk-Version", "2.1.0.cn")
            .addHeader("Passport-Sdk-Version", "30390")
            .addHeader("X-Tt-Token","0001be99977e2da6c7de0c82114bc9db07026501c9f789b6986bc8e894459ce7b26f6b662c150f38eebe757864e731c2d99dc86ccc2c93881e380e79476b41fe06b327f7cf73bfee26d87b794588b409bc0cfc22c9c1d177ec836b760cbc4b5fce777-1.0.1")
            .addHeader("Sdk-Version", "2")
            .addHeader("Content-Type","application/x-protobuf")
            .addHeader("X-Ss-Stub", "4D3401BA90996C6DB1BD8DC88E821601")
            .addHeader("X-Tt-Trace-Id", "00-1c6d71850dd886849983a17a60c2ffff-1c6d71850dd88684-01")
            .addHeader("User-Agent","com.xuexiaoyi.xxy/10402 (Linux; U; Android 7.1.1; zh_CN; CDY-AN00; Build/N6F26Q; Cronet/TTNetVersion:90dd7cdc 2021-11-10 QuicVersion:68cae75d 2021-08-12)")
            .addHeader("X-Ladon", "dnHlUjSMB8p3KAxeJjnJDMwSGunz85c+Cllg6jaL0zgFkGIL")
            .addHeader("X-Gorgon", "040480f40000d5d3ef282fa95f7bca5a27fd9d3bb0c720814a2d")
            .addHeader("X-Khronos", "1645449409")
            .addHeader("X-Argus", "v6aGSj2SOaJfusldB5DtpE1Wd6IeSLa73jWQIy3a6n/w5YGZiy5EhvYN+yx2xlypXuvpHD+qyoPJKecptDJKZxNrWYQUwkG/m1Wue72IGzV2QulVabF4F+lCUvI2fz5PeiKywQ/AMJ+tKg22/h5FRYfIYfesErPjpUJPokRa/rc1J3RopVNNun0KIxuWqWdB+WOlZpxlrHkoc+OfetPKuuX2fQ8eSRos0BDyvIUAssPEEZ4+zHb/1n/SDsJJTD0fv/H2Dut9c8+x1w8vlKX8h5cP")
            .post(reqbody)
            .build()
        thread {
            val res=client.newCall(req).execute()
            val result=Res.Pb.parseFrom(res.body?.bytes()).a
            val span=SpannableStringBuilder()
            var i=0
            while (i<=5){
                span.append(result.bList[i].c.d4+"\n",ForegroundColorSpan(Color.GRAY),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                span.append(result.bList[i].c.d5+"\n")
                i++
            }
            span.setSpan(RelativeSizeSpan(0.5f),0,span.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            popup.setText(span)
            popup.show()
        }
    }
}