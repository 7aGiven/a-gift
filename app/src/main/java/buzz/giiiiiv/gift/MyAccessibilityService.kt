package buzz.giiiiiv.gift

import android.accessibilityservice.AccessibilityService
import android.graphics.Color
import android.text.*
import android.text.style.ForegroundColorSpan
import android.view.accessibility.AccessibilityEvent
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
                val node=getRootInActiveWindow()
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
                System.out.println(question)
                http(question)
            }catch (e: NullPointerException){System.out.println(e)}
        }
    }
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
            .addHeader("Cookie","uid_tt=ec18b4ba97adaf1c23d317b3e95a77ce; uid_tt_ss=ec18b4ba97adaf1c23d317b3e95a77ce; sid_guard=b1f48c82652ccfc5f8871fab93fdabe3%7C1636365223%7C5183794%7CFri%2C+07-Jan-2022+09%3A50%3A17+GMT; sid_tt=b1f48c82652ccfc5f8871fab93fdabe3; sessionid=b1f48c82652ccfc5f8871fab93fdabe3; sessionid_ss=b1f48c82652ccfc5f8871fab93fdabe3; odin_tt=92eaea78ce83e534f270ad4d9debe66bef2ee19964ac73d6b683b33d6083b06576c11fa1e89061dfa91c6b1db1f2c4fd7f7435f03e5c0d1c35c40dfaf4942e2b")
            .addHeader("X-Vc-Bdturing-Sdk-Version", "2.1.0.cn")
            .addHeader("Passport-Sdk-Version", "21")
            .addHeader("X-Tt-Token","00b1f48c82652ccfc5f8871fab93fdabe305ec57cf034dab0ba90ab21570ca2a2dc23aa0d48a607cc0bfb8064c1dfbb078866c4c9f49705900b7bce01ce66b04a5a8496c4e14e2b0617908d8d62eac1142ef140a0287875a35d55f1d29a6db3818903-1.0.1")
            .addHeader("Sdk-Version", "2")
            .addHeader("Content-Type","application/x-protobuf")
            .addHeader("X-Ss-Stub", "F7A1B4087DA3FC1D4602F8413100DA23")
            .addHeader("X-Tt-Trace-Id", "00-143043100dd886849983a17007fdffff-143043100dd88684-01")
            .addHeader("User-Agent","com.xuexiaoyi.xxy/10300 (Linux; U; Android 7.1.1; zh_CN; CDY-AN00; Build/N6F26Q; Cronet/TTNetVersion:921ec9e4 2021-07-19 QuicVersion:6ad2ee95 2021-04-06)")
            .addHeader("X-Argus", "LmpQuw8NOV/pXQ5js1+B9nqETO9erpRjHEUYPED2DpBkrw+v8ntk+nnwo8ZaXx5K88fJD94jqt/FMGpClfVtFtsgwZI5qogUWPV1kpn7NXXoD6+UKC/fw5Ygum8p9GehmvyUvafVuTdFHGIxR9nQmIXypX9GRh36owDrfwl7D12yo/29Ob0UCysffoSaKb3nF+/9YFSzYUdTzKKtDpzZ5klnyzFq31j/I3RwVPhk/6veswzqUgQ+KG0IYJFtIB9cisnHzgdmBHCE6ZXNjKLzh8bB")
            .addHeader("X-Gorgon", "0404400700009fc49bbd8f853c39e9c3a117fd5a0ae59832f7e0")
            .addHeader("X-Khronos", "1636721246")
            .addHeader("X-Ladon", " LrvQcJzpqz0mha1ZW2vVqDgi7wNl025bBherG4BNt9obMSe7")
            .post(reqbody)
            .build()
        thread {
            val res=client.newCall(req).execute()
            val result=Res.Pb.parseFrom(res.body?.bytes()).a
            val span=SpannableStringBuilder()
            var i=0
            while (i<=5){
                span.append(result.bList[i].c.d4+"\n")
                span.append(result.bList[i].c.d5+"\n",ForegroundColorSpan(Color.RED),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                i++
            }
            System.out.println(span)
            popup.setText(span)
            popup.show()
        }
    }
}