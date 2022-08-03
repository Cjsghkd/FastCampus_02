package com.example.a30androidwithkotlie

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Ch7_MainActivity : AppCompatActivity() {

    private val recordButton : Ch7_RecordButton by lazy {
        findViewById(R.id.recordButton)
    }

    private var state = Ch7_State.BEFORE_RECORDING
        set(value) {
            field = value
            resetButton.isEnabled = (value == Ch7_State.AFTER_RECORDING) ||
                    (value == Ch7_State.ON_PLAYING)
            recordButton.updateIconWithState(value)
    }

    private val requiredPermissions = arrayOf(Manifest.permission.RECORD_AUDIO)

    private var recorder : MediaRecorder? = null

    private val recordingFilePath : String by lazy {
        "${externalCacheDir?.absolutePath}/recording.3gp"
    }

    private var player : MediaPlayer? = null

    private val resetButton : Button by lazy {
        findViewById(R.id.resetButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ch7_main)

        requestAudioPermission()
        initView()
        bindViews()
        initVariables()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRequestPermissionGranted =
            requestCode == REQUEST_RECORD_AUDIO_PERMISSION &&
                grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (!audioRequestPermissionGranted) {
            finish()
        }
    }

    private fun requestAudioPermission() {
        requestPermissions(requiredPermissions, REQUEST_RECORD_AUDIO_PERMISSION)
    }

    private fun initView() {
        recordButton.updateIconWithState(state)
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(recordingFilePath)
            prepare()
        }
        recorder?.start()
        state = Ch7_State.ON_RECORDING
    }

    private fun stopRecording() {
        recorder?.run {
            stop()
            release()
        }
        recorder = null
        state = Ch7_State.AFTER_RECORDING
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            setDataSource(recordingFilePath)
            prepare()
        }
        player?.start()
        state = Ch7_State.ON_PLAYING
    }

    private fun stopPlaying() {
        player?.release()
        player = null
        state = Ch7_State.AFTER_RECORDING
    }

    private fun bindViews() {
        resetButton.setOnClickListener {
            stopPlaying()
            state = Ch7_State.BEFORE_RECORDING
        }

        recordButton.setOnClickListener {
            when(state) {
                Ch7_State.BEFORE_RECORDING -> {
                    startRecording()
                }
                Ch7_State.ON_RECORDING -> {
                    stopRecording()
                }
                Ch7_State.AFTER_RECORDING -> {
                    startPlaying()
                }
                Ch7_State.ON_PLAYING -> {
                    stopPlaying()
                }
            }
        }
    }

    private fun initVariables() {
        state = Ch7_State.BEFORE_RECORDING
    }

    companion object {
        private const val REQUEST_RECORD_AUDIO_PERMISSION = 201
    }
}