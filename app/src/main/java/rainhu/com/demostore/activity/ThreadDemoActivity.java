package rainhu.com.demostore.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import rainhu.com.demostore.R;
import rainhu.com.demostore.logger.Log;

/**
 * Created by huzhengyu on 16-11-22.
 */

/***
 * HandlerThread
 * HandlerThread实际上是带有Looper的thread,
 * 启动方式
 * HandlerThread handlerThread = new HandlerThread("test);
 * handlerThread.start();
 * 在不需要线程Loop的时候需要调用HandlerThread.quitSafely() 或 HandlerThread.quit();
 *
 * Thread thread = ne Thread(new Runnable(){
 *     @Override
 *     public void run(){
 *         Looper.prepare();
 *         // do works
 *         ///...
 *         Looper.loop();
 *     }
 * });
 * thread.start();
 *
 *
 *
 */
public class ThreadDemoActivity extends LoggingActivity{
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //do time-consume work
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaddemo);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("hzy","test");

        connect();

    }

    private void connect() {
        String myString = null;
        try{
            URL myURL = new URL("http://www.google.com/robots.txt");
            URLConnection ucon = myURL.openConnection();
            InputStream is = ucon.getInputStream();

        } catch (MalformedURLException e) {
            Log.e("hzy","e:"+e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("hzy","e:"+e.toString());
            e.printStackTrace();
        }

    }

    //basic use of handler

    public void doOperation(){

        //从global线程池中获取message对象，很多情况下能够避免创建新的message对象
        Message msg = Message.obtain();
        msg.arg1 = 1;
        msg.arg2 = 2;
        msg.what = 3;
        msg.obj = new Object();
        //mHandler.sendMessage(msg);
        mHandler.sendMessageDelayed(msg,1000);
        //mHandler.sendEmptyMessage(int what);
    }



}
