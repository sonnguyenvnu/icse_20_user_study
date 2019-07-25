package com.shuyu.waveview;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shuyu on 2016/11/15.
 * 声音播放
 */
public class AudioPlayer implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

    public final static int HANDLER_CUR_TIME = 1; //当�?播放状�?时间
    public final static int HANDLER_PREPARED = 2;//装备好了
    public final static int HANDLER_COMPLETE = 0;//完�?
    public final static int HANDLER_ERROR = -28;//错误


    private MediaPlayer mMediaPlayer;
    private Handler mRemoteHandler;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private AudioManager mAudioManager;


    /**
     * 音频播放器
     *
     * @param context 上下文
     * @param handler 音频状�?handler
     */
    public AudioPlayer(Context context, Handler handler) {
        super();
        this.mRemoteHandler = handler;
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体�?类型
            mMediaPlayer.setOnBufferingUpdateListener(this);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnCompletionListener(this);
            mMediaPlayer.setOnErrorListener(this);
            mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        mAudioManager.setMode(AudioManager.MODE_NORMAL);
        if(mMediaPlayer != null)
            mMediaPlayer.start();
    }

    /**
     * @param url url地�?�
     */
    public int playUrl(String url) {
        try {
            mAudioManager.setMode(AudioManager.MODE_NORMAL);
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(url); // 设置数�?��?
            mMediaPlayer.prepareAsync(); // prepare自动播放
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return -1;
        } catch (SecurityException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (mMediaPlayer == null || !mMediaPlayer.isPlaying()) {
                    return;
                }
                Message msg = new Message();
                msg.obj = mMediaPlayer.getCurrentPosition();
                msg.what = 1;
                mRemoteHandler.sendMessageAtTime(msg, 0);
            }
        };
        mTimer.schedule(mTimerTask, 0, 10);

        return 100;
    }

    // 暂�?�
    public void pause() {
        if(mMediaPlayer != null)
            mMediaPlayer.pause();
    }

    // �?�止
    public void stop() {
        if (mTimer != null) {
            mTimer.cancel();
        }
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void seekTo(int time) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(time);
            mMediaPlayer.start();
        }
    }

    // 播放准备
    @Override
    public void onPrepared(MediaPlayer mp) {
        if (mRemoteHandler != null) {
            Message msg = new Message();
            msg.obj = mMediaPlayer.getDuration();
            msg.what = 2;
            mRemoteHandler.sendMessageAtTime(msg, 0);
        }
        mp.start();

    }

    // 播放完�?
    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mRemoteHandler != null) {
            Message msg = new Message();
            msg.what = 0;
            mRemoteHandler.sendMessageAtTime(msg, 0);
        }
    }

    /**
     * 缓冲更新
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
    }


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (mRemoteHandler != null) {
            Message msg = new Message();
            msg.what = -28;
            mRemoteHandler.sendMessageAtTime(msg, 0);
        }
        return false;
    }

    public long getDuration() {
        return mMediaPlayer.getDuration();
    }

    /**
     * 获�?�当�?播放时长
     *
     * @return 本地播放时长
     */
    public static long getDurationLocation(Context context, String path) {
        MediaPlayer player = MediaPlayer.create(context, Uri.fromFile(new File(path)));
        if (player != null)
            return player.getDuration();
        else
            return 0;
    }

}

