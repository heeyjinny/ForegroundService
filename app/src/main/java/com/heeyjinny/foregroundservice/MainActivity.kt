package com.heeyjinny.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.heeyjinny.foregroundservice.databinding.ActivityMainBinding

//1
//포어그라운드 서비스를 사용하기 위해
//AndroidManifest.xml 포어그라운드 권한 추가

//2
//app - java 패키지명 우클릭 - New - Service - Service
//Foreground.kt 생성

class MainActivity : AppCompatActivity() {

    //뷰바인딩
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //3
        //시작버튼 클릭 시 서비스 시작코드 작성
        binding.buttonStart.setOnClickListener {
            //3-1
            //인텐트 생성
            val intent = Intent(this, Foreground::class.java)
            //3-2
            //포어그라운드 서비스 실행 메서드 작성
            //startService()가 아닌
            //ContextCompat.startForegroundService()사용
            ContextCompat.startForegroundService(this, intent)
        }

        //4
        //종료버튼 클릭 시 서비스 종료코드 작성
        binding.buttonStop.setOnClickListener {
            //4-1
            //인텐트 생성하고 종료 메서드 작성하여 인텐트 전달
            val intent = Intent(this, Foreground::class.java)
            stopService(intent)
        }

        //5
        //에뮬레이터 실행 후 확인
        //상단에 아이콘이 떠있고 알림창 생성됨
        //포어그라운드는 사용자에게 현재 서비스가 실행 중임을 항상 알려줘야 함
        //실행한 액티비티를 강제 종료해도 서비스가 실행되기 때문에 알림이 사라지지 않음

    }//onCreate
}//MainActivity