package com.heeyjinny.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class Foreground : Service() {

    //2
    //포어그라운드 서비스를 사용하기 위해서는 상태바에 알림을 띄워야함
    //이 알림이 사용할 채널을 설정할 때 사용하는 상수 정의
    val CHANNEL_ID = "ForegroundChannel"

    override fun onBind(intent: Intent): IBinder {
        //1
        //오류를 막기위해 비어있는 바인더 리턴
        return Binder()
    }//onBind

    //3
    //포어그라운드 서비스에 사용할 알림 실행 전
    //알림채널을 생성하는 메서드 생성
    //모든 알림은 채널 단위로 동작하도록 설계되어 있음
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    //4
    //onStartCommand()메서드 오버라이드
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //4-1
        //앞에서 만들어둔 메서드를 호출해 알림채널 생성
        createNotificationChannel()

        //4-2
        //알림 생성: 알림제목, 알림 아이콘 설정
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()

        //4-3
        //알림 실행 메서드 사용
        startForeground(1, notification)

        return super.onStartCommand(intent, flags, startId)
    }

    //5
    //서비스 실행할 버튼 배치
    //activity_main.xml 에서 버튼 생성

}//Foreground