package com.zlm.hp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlm.hp.db.SongSingerDB;
import com.zlm.hp.libs.utils.ColorUtil;
import com.zlm.hp.lyrics.LyricsReader;
import com.zlm.hp.lyrics.widget.AbstractLrcView;
import com.zlm.hp.lyrics.widget.ManyLyricsView;
import com.zlm.hp.manager.AudioPlayerManager;
import com.zlm.hp.manager.LyricsManager;
import com.zlm.hp.model.AudioInfo;
import com.zlm.hp.model.AudioMessage;
import com.zlm.hp.model.SongSingerInfo;
import com.zlm.hp.receiver.AudioBroadcastReceiver;
import com.zlm.hp.utils.AniUtil;
import com.zlm.hp.utils.ImageUtil;
import com.zlm.hp.widget.SingerImageView;
import com.zlm.hp.widget.lock.LockButtonRelativeLayout;
import com.zlm.hp.widget.lock.LockPalyOrPauseButtonRelativeLayout;
import com.zlm.libs.widget.SwipeBackLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Description: é”?å±?ç•Œé?¢
 * @Param:
 * @Return:
 * @Author: zhangliangming
 * @Date: 2018/1/19 16:07
 * @Throws:
 */
public class LockActivity extends BaseActivity {

    private final int HASTRANSLATELRC = 0;
    private final int HASTRANSLITERATIONLRC = 1;
    private final int HASTRANSLATEANDTRANSLITERATIONLRC = 2;
    private final int NOEXTRALRC = 3;
    private final int INITDATA = 1;
    /**
     *
     */
    private SwipeBackLayout mSwipeBackLayout;
    /**
     * æ»‘åŠ¨æ??ç¤ºå›¾æ ‡
     */
    private ImageView lockImageView;
    private AnimationDrawable aniLoading;
    /**
     * æ—¶é—´
     */
    private TextView timeTextView;
    /**
     * æ—¥æœŸ
     */
    private TextView dateTextView;
    /**
     * æ˜ŸæœŸå‡ 
     */
    private TextView dayTextView;
    /**
     * æ­Œå??
     */
    private TextView songNameTextView;
    /**
     * æ­Œæ‰‹
     */
    private TextView songerTextView;
    //æš‚å?œã€?æ’­æ”¾å›¾æ ‡
    private ImageView playImageView;
    private ImageView pauseImageView;
    /**
     * ä¸Šä¸€é¦–æŒ‰é’®
     */
    private LockButtonRelativeLayout prewButton;
    /**
     * ä¸‹ä¸€é¦–æŒ‰é’®
     */
    private LockButtonRelativeLayout nextButton;
    /**
     * æ’­æ”¾æˆ–è€…æš‚å?œæŒ‰é’®
     */
    private LockPalyOrPauseButtonRelativeLayout playOrPauseButton;
    /**
     * éŸ³é¢‘å¹¿æ’­
     */
    private AudioBroadcastReceiver mAudioBroadcastReceiver;
    /**
     * åˆ†é’Ÿå¹¿æ’­
     */
    private Handler mTimeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            setDate();
        }
    };
    /**
     * åˆ†é’Ÿå?˜åŒ–å¹¿æ’­
     */
    private BroadcastReceiver mTimeReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
                mTimeHandler.sendEmptyMessage(0);
            }
        }
    };
    /**
     * æ­Œæ‰‹å†™çœŸ
     */
    private SingerImageView mSingerImageView;
    /**
     * å¤šè¡Œæ­Œè¯?è§†å›¾
     */
    private ManyLyricsView mManyLineLyricsView;
    /**
     * å¹¿æ’­ç›‘å?¬
     */
    private AudioBroadcastReceiver.AudioReceiverListener mAudioReceiverListener = new AudioBroadcastReceiver.AudioReceiverListener() {
        @Override
        public void onReceive(Context context, Intent intent) {
            doAudioReceive(context, intent);
        }
    };
    //ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ç¿»è¯‘å’ŒéŸ³è¯‘æ­Œè¯?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?
    //ç¿»è¯‘æ­Œè¯?
    private ImageView mHideTranslateImg;
    private ImageView mShowTranslateImg;
    //éŸ³è¯‘æ­Œè¯?
    private ImageView mHideTransliterationImg;
    private ImageView mShowTransliterationImg;
    //ç¿»è¯‘æ­Œè¯?/éŸ³è¯‘æ­Œè¯?
    private ImageView mShowTTToTranslateImg;
    private ImageView mShowTTToTransliterationImg;
    private ImageView mHideTTImg;
    /**
     * å±?å¹•å®½åº¦
     */
    private int mScreensWidth;
    private Handler mExtraLrcTypeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NOEXTRALRC:

                    //ç¿»è¯‘æ­Œè¯?
                    mHideTranslateImg.setVisibility(View.INVISIBLE);
                    mShowTranslateImg.setVisibility(View.INVISIBLE);
                    //éŸ³è¯‘æ­Œè¯?
                    mHideTransliterationImg.setVisibility(View.INVISIBLE);
                    mShowTransliterationImg.setVisibility(View.INVISIBLE);

                    //ç¿»è¯‘æ­Œè¯?/éŸ³è¯‘æ­Œè¯?
                    mShowTTToTranslateImg.setVisibility(View.INVISIBLE);
                    mShowTTToTransliterationImg.setVisibility(View.INVISIBLE);
                    mHideTTImg.setVisibility(View.INVISIBLE);


                    break;
                case HASTRANSLATEANDTRANSLITERATIONLRC:


                    //ç¿»è¯‘æ­Œè¯?
                    mHideTranslateImg.setVisibility(View.INVISIBLE);
                    mShowTranslateImg.setVisibility(View.INVISIBLE);
                    //éŸ³è¯‘æ­Œè¯?
                    mHideTransliterationImg.setVisibility(View.INVISIBLE);
                    mShowTransliterationImg.setVisibility(View.INVISIBLE);


                    //ç¿»è¯‘æ­Œè¯?/éŸ³è¯‘æ­Œè¯?
                    mShowTTToTranslateImg.setVisibility(View.INVISIBLE);
                    mShowTTToTransliterationImg.setVisibility(View.INVISIBLE);
                    mHideTTImg.setVisibility(View.VISIBLE);

                    break;
                case HASTRANSLITERATIONLRC:

                    //ç¿»è¯‘æ­Œè¯?
                    mHideTranslateImg.setVisibility(View.INVISIBLE);
                    mShowTranslateImg.setVisibility(View.INVISIBLE);


                    //éŸ³è¯‘æ­Œè¯?
                    mHideTransliterationImg.setVisibility(View.VISIBLE);
                    mShowTransliterationImg.setVisibility(View.INVISIBLE);


                    //ç¿»è¯‘æ­Œè¯?/éŸ³è¯‘æ­Œè¯?
                    mShowTTToTranslateImg.setVisibility(View.INVISIBLE);
                    mShowTTToTransliterationImg.setVisibility(View.INVISIBLE);
                    mHideTTImg.setVisibility(View.INVISIBLE);

                    break;
                case HASTRANSLATELRC:


                    //ç¿»è¯‘æ­Œè¯?
                    mHideTranslateImg.setVisibility(View.VISIBLE);
                    mShowTranslateImg.setVisibility(View.INVISIBLE);


                    //éŸ³è¯‘æ­Œè¯?
                    mHideTransliterationImg.setVisibility(View.INVISIBLE);
                    mShowTransliterationImg.setVisibility(View.INVISIBLE);

                    //ç¿»è¯‘æ­Œè¯?/éŸ³è¯‘æ­Œè¯?
                    mShowTTToTranslateImg.setVisibility(View.INVISIBLE);
                    mShowTTToTransliterationImg.setVisibility(View.INVISIBLE);
                    mHideTTImg.setVisibility(View.INVISIBLE);


                    break;

            }

        }
    };
    /**
     *
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case INITDATA:
                    initData();
                    break;
            }
        }
    };


    //ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ç¿»è¯‘å’ŒéŸ³è¯‘æ­Œè¯?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?ã€?


    @Override
    protected int setContentViewId() {
        return R.layout.activity_lock;
    }

    @Override
    protected void preLoad() {
        super.preLoad();
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void contentViewFinish(View contentView) {
        //
        mSwipeBackLayout = contentView.findViewById(R.id.swipeback_layout);
        mSwipeBackLayout.setContentView(R.layout.activity_lock_layout, SwipeBackLayout.CONTENTVIEWTYPE_RELATIVELAYOUT);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //

        mSwipeBackLayout.setSwipeBackLayoutListener(new SwipeBackLayout.SwipeBackLayoutListener() {
            @Override
            public void finishActivity() {
                finish();
                overridePendingTransition(0, 0);
            }
        });

        //æ??ç¤ºå?³æ»‘åŠ¨å›¾æ ‡
        lockImageView = findViewById(R.id.tip_image);
        aniLoading = (AnimationDrawable) lockImageView.getBackground();

        //æ—¶é—´
        timeTextView = findViewById(R.id.time);
        dateTextView = findViewById(R.id.date);
        dayTextView = findViewById(R.id.day);

        //æ­Œæ‰‹ä¸Žæ­Œå??
        songNameTextView = findViewById(R.id.songName);
        songerTextView = findViewById(R.id.songer);


        playImageView = findViewById(R.id.play);
        pauseImageView = findViewById(R.id.pause);
        //æ’­æ”¾æŒ‰é’®ã€?ä¸Šä¸€é¦–ï¼Œä¸‹ä¸€é¦–
        prewButton = findViewById(R.id.prev_button);
        prewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                Intent preIntent = new Intent(AudioBroadcastReceiver.ACTION_PREMUSIC);
                preIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(preIntent);

            }
        });

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                Intent nextIntent = new Intent(AudioBroadcastReceiver.ACTION_NEXTMUSIC);
                nextIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(nextIntent);

            }
        });

        playOrPauseButton = findViewById(R.id.play_pause_button);
        playOrPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int playStatus = mHPApplication.getPlayStatus();
                if (playStatus == AudioPlayerManager.PLAYING) {

                    Intent resumeIntent = new Intent(AudioBroadcastReceiver.ACTION_PAUSEMUSIC);
                    resumeIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(resumeIntent);

                } else {
                    if (playStatus == AudioPlayerManager.PAUSE) {

                        AudioInfo audioInfo = mHPApplication.getCurAudioInfo();
                        if (audioInfo != null) {

                            AudioMessage audioMessage = mHPApplication.getCurAudioMessage();
                            Intent resumeIntent = new Intent(AudioBroadcastReceiver.ACTION_RESUMEMUSIC);
                            resumeIntent.putExtra(AudioMessage.KEY, audioMessage);
                            resumeIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                            sendBroadcast(resumeIntent);

                        }

                    } else {
                        if (mHPApplication.getCurAudioMessage() != null) {
                            AudioMessage audioMessage = mHPApplication.getCurAudioMessage();
                            AudioInfo audioInfo = mHPApplication.getCurAudioInfo();
                            if (audioInfo != null) {
                                audioMessage.setAudioInfo(audioInfo);
                                Intent resumeIntent = new Intent(AudioBroadcastReceiver.ACTION_PLAYMUSIC);
                                resumeIntent.putExtra(AudioMessage.KEY, audioMessage);
                                resumeIntent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                                sendBroadcast(resumeIntent);
                            }
                        }
                    }
                }
            }
        });


        //æ­Œæ‰‹å†™çœŸ
        mSingerImageView = findViewById(R.id.singerimg);
        mSingerImageView.setVisibility(View.INVISIBLE);

        //å¤šè¡Œæ­Œè¯?

        //
        mManyLineLyricsView = findViewById(R.id.lock_manyLineLyricsView);
        //ä¸?èƒ½è§¦æ‘¸å’Œç‚¹å‡»äº‹ä»¶
        mManyLineLyricsView.setTouchAble(false);
        //ç¿»è¯‘æ­Œè¯?
        mHideTranslateImg = findViewById(R.id.hideTranslateImg);
        mHideTranslateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHideTranslateImg.setVisibility(View.INVISIBLE);
                mShowTranslateImg.setVisibility(View.VISIBLE);


                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.setExtraLrcStatus(AbstractLrcView.EXTRALRCSTATUS_NOSHOWEXTRALRC);
                }

            }
        });
        mShowTranslateImg = findViewById(R.id.showTranslateImg);
        mShowTranslateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHideTranslateImg.setVisibility(View.VISIBLE);
                mShowTranslateImg.setVisibility(View.INVISIBLE);

                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.setExtraLrcStatus(AbstractLrcView.EXTRALRCSTATUS_SHOWTRANSLATELRC);

                }

            }
        });
        //éŸ³è¯‘æ­Œè¯?
        mHideTransliterationImg = findViewById(R.id.hideTransliterationImg);
        mHideTransliterationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHideTransliterationImg.setVisibility(View.INVISIBLE);
                mShowTransliterationImg.setVisibility(View.VISIBLE);

                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.setExtraLrcStatus(AbstractLrcView.EXTRALRCSTATUS_NOSHOWEXTRALRC);
                }

            }
        });
        mShowTransliterationImg = findViewById(R.id.showTransliterationImg);
        mShowTransliterationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHideTransliterationImg.setVisibility(View.VISIBLE);
                mShowTransliterationImg.setVisibility(View.INVISIBLE);

                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.setExtraLrcStatus(AbstractLrcView.EXTRALRCSTATUS_SHOWTRANSLITERATIONLRC);
                }
            }
        });

        //ç¿»è¯‘æ­Œè¯?/éŸ³è¯‘æ­Œè¯?
        mShowTTToTranslateImg = findViewById(R.id.showTTToTranslateImg);
        mShowTTToTranslateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowTTToTranslateImg.setVisibility(View.INVISIBLE);
                mShowTTToTransliterationImg.setVisibility(View.VISIBLE);
                mHideTTImg.setVisibility(View.INVISIBLE);

                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.setExtraLrcStatus(AbstractLrcView.EXTRALRCSTATUS_SHOWTRANSLATELRC);
                }
            }
        });
        mShowTTToTransliterationImg = findViewById(R.id.showTTToTransliterationImg);
        mShowTTToTransliterationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowTTToTranslateImg.setVisibility(View.INVISIBLE);
                mShowTTToTransliterationImg.setVisibility(View.INVISIBLE);
                mHideTTImg.setVisibility(View.VISIBLE);


                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.setExtraLrcStatus(AbstractLrcView.EXTRALRCSTATUS_SHOWTRANSLITERATIONLRC);
                }
            }
        });
        mHideTTImg = findViewById(R.id.hideTTImg);
        mHideTTImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShowTTToTranslateImg.setVisibility(View.VISIBLE);
                mShowTTToTransliterationImg.setVisibility(View.INVISIBLE);
                mHideTTImg.setVisibility(View.INVISIBLE);

                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.setExtraLrcStatus(AbstractLrcView.EXTRALRCSTATUS_NOSHOWEXTRALRC);
                }
            }
        });


        //è®¾ç½®é¢?å¤–æ­Œè¯?å›žè°ƒäº‹ä»¶
        mManyLineLyricsView.setExtraLyricsListener(new AbstractLrcView.ExtraLyricsListener() {
            @Override
            public void extraLrcCallback() {
                int extraLrcType = mManyLineLyricsView.getExtraLrcType();
                if (extraLrcType == AbstractLrcView.EXTRALRCTYPE_NOLRC) {
                    mExtraLrcTypeHandler.sendEmptyMessage(NOEXTRALRC);
                } else if (extraLrcType == AbstractLrcView.EXTRALRCTYPE_TRANSLATELRC) {
                    mExtraLrcTypeHandler.sendEmptyMessage(HASTRANSLATELRC);
                } else if (extraLrcType == AbstractLrcView.EXTRALRCTYPE_TRANSLITERATIONLRC) {
                    mExtraLrcTypeHandler.sendEmptyMessage(HASTRANSLITERATIONLRC);
                } else if (extraLrcType == AbstractLrcView.EXTRALRCTYPE_BOTH) {
                    mExtraLrcTypeHandler.sendEmptyMessage(HASTRANSLATEANDTRANSLITERATIONLRC);
                }


            }
        });


        //
        //è®¾ç½®å­—ä½“å¤§å°?å’Œæ­Œè¯?é¢œè‰²
        mManyLineLyricsView.setSize(mHPApplication.getLrcFontSize(), mHPApplication.getLrcFontSize(), false);
        int lrcColor = ColorUtil.parserColor(mHPApplication.getLrcColorStr()[mHPApplication.getLrcColorIndex()]);
        mManyLineLyricsView.setPaintHLColor(new int[]{lrcColor, lrcColor}, false);
        mManyLineLyricsView.setPaintColor(new int[]{Color.WHITE, Color.WHITE}, false);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        mScreensWidth = display.getWidth();

        //æ³¨å†Œå¹¿æ’­
        registerReceiver();

    }

    private void registerReceiver() {


        //æ³¨å†ŒæŽ¥æ”¶éŸ³é¢‘æ’­æ”¾å¹¿æ’­
        mAudioBroadcastReceiver = new AudioBroadcastReceiver(getApplicationContext(), mHPApplication);
        mAudioBroadcastReceiver.setAudioReceiverListener(mAudioReceiverListener);
        mAudioBroadcastReceiver.registerReceiver(getApplicationContext());

        //æ³¨å†Œåˆ†é’Ÿå?˜åŒ–å¹¿æ’­
        IntentFilter mTimeFilter = new IntentFilter();
        mTimeFilter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(mTimeReceiver, mTimeFilter);
    }

    @Override
    protected void loadData(boolean isRestoreInstance) {
        mHandler.sendEmptyMessage(INITDATA);
    }

    /**
     * åˆ?å§‹åŒ–æ•°æ?®
     */
    private void initData() {
        AniUtil.startAnimation(aniLoading);
        setDate();

        //åŠ è½½éŸ³é¢‘æ•°æ?®
        AudioInfo curAudioInfo = mHPApplication.getCurAudioInfo();
        if (curAudioInfo != null) {
            Intent initIntent = new Intent(AudioBroadcastReceiver.ACTION_INITMUSIC);
            doAudioReceive(getApplicationContext(), initIntent);
        } else {
            Intent nullIntent = new Intent(AudioBroadcastReceiver.ACTION_NULLMUSIC);
            doAudioReceive(getApplicationContext(), nullIntent);
        }
    }

    /**
     * è®¾ç½®æ—¥æœŸ
     */
    private void setDate() {

        String str = "";
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

        Calendar lastDate = Calendar.getInstance();
        str = sdfDate.format(lastDate.getTime());
        dateTextView.setText(str);
        str = sdfTime.format(lastDate.getTime());
        timeTextView.setText(str);

        String mWay = String.valueOf(lastDate.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "æ—¥";
        } else if ("2".equals(mWay)) {
            mWay = "ä¸€";
        } else if ("3".equals(mWay)) {
            mWay = "äºŒ";
        } else if ("4".equals(mWay)) {
            mWay = "ä¸‰";
        } else if ("5".equals(mWay)) {
            mWay = "å››";
        } else if ("6".equals(mWay)) {
            mWay = "äº”";
        } else if ("7".equals(mWay)) {
            mWay = "å…­";
        }
        dayTextView.setText("æ˜ŸæœŸ" + mWay);

    }


    /**
     * å¤„ç?†éŸ³é¢‘å¹¿æ’­äº‹ä»¶
     *
     * @param context
     * @param intent
     */
    private void doAudioReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(AudioBroadcastReceiver.ACTION_NULLMUSIC)) {
            //ç©ºæ•°æ?®

            songNameTextView.setText(R.string.def_songName);
            songerTextView.setText(R.string.def_artist);

            playImageView.setVisibility(View.VISIBLE);
            pauseImageView.setVisibility(View.INVISIBLE);

            playOrPauseButton.setPlayingProgress(0);
            playOrPauseButton.setMaxProgress(0);
            playOrPauseButton.invalidate();


            //æ­Œæ‰‹å†™çœŸ
            mSingerImageView.setVisibility(View.INVISIBLE);
            mSingerImageView.setSongSingerInfos(mHPApplication, getApplicationContext(), null);

            //
            mManyLineLyricsView.initLrcData();


        } else if (action.equals(AudioBroadcastReceiver.ACTION_INITMUSIC)) {

            //åˆ?å§‹åŒ–
            AudioMessage audioMessage = mHPApplication.getCurAudioMessage();//(AudioMessage) intent.getSerializableExtra(AudioMessage.KEY);
            AudioInfo audioInfo = mHPApplication.getCurAudioInfo();


            songNameTextView.setText(audioInfo.getSongName());
            songerTextView.setText(audioInfo.getSingerName());

            if (mHPApplication.getPlayStatus() == AudioPlayerManager.PLAYING) {
                playImageView.setVisibility(View.INVISIBLE);
                pauseImageView.setVisibility(View.VISIBLE);
            } else {
                playImageView.setVisibility(View.VISIBLE);
                pauseImageView.setVisibility(View.INVISIBLE);
            }

            playOrPauseButton.setMaxProgress((int) audioInfo
                    .getDuration());
            playOrPauseButton.setPlayingProgress((int) audioMessage.getPlayProgress());
            playOrPauseButton.invalidate();


            //
            mSingerImageView.setVisibility(View.INVISIBLE);
            mSingerImageView.setSongSingerInfos(mHPApplication, getApplicationContext(), null);
            //åŠ è½½æ­Œæ‰‹å†™çœŸ
            ImageUtil.loadSingerImg(mHPApplication, getApplicationContext(), audioInfo.getHash(), audioInfo.getSingerName());


            //åŠ è½½æ­Œè¯?
            String keyWords = "";
            if (audioInfo.getSingerName().equals("æœªçŸ¥")) {
                keyWords = audioInfo.getSongName();
            } else {
                keyWords = audioInfo.getSingerName() + " - " + audioInfo.getSongName();
            }
            LyricsManager.getLyricsManager(mHPApplication, getApplicationContext()).loadLyricsUtil(keyWords, keyWords, audioInfo.getDuration() + "", audioInfo.getHash());

            //
            mManyLineLyricsView.initLrcData();
            //åŠ è½½ä¸­
            mManyLineLyricsView.setLrcStatus(AbstractLrcView.LRCSTATUS_LOADING);

        } else if (action.equals(AudioBroadcastReceiver.ACTION_SERVICE_PLAYMUSIC)) {

            //æ’­æ”¾

            AudioMessage audioMessage = mHPApplication.getCurAudioMessage();//(AudioMessage) intent.getSerializableExtra(AudioMessage.KEY);

            if (pauseImageView.getVisibility() != View.VISIBLE) {
                pauseImageView.setVisibility(View.VISIBLE);
            }
            if (playImageView.getVisibility() != View.INVISIBLE) {
                playImageView.setVisibility(View.INVISIBLE);
            }
            playOrPauseButton.setPlayingProgress((int) audioMessage.getPlayProgress());
            playOrPauseButton.invalidate();

            if (audioMessage != null) {


                AudioInfo audioInfo = mHPApplication.getCurAudioInfo();
                if (audioInfo != null) {

                    //æ›´æ–°æ­Œè¯?

                    if (mManyLineLyricsView.getLyricsReader() != null && mManyLineLyricsView.getLyricsReader().getHash().equals(audioInfo.getHash()) && mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC && mManyLineLyricsView.getLrcPlayerStatus() != AbstractLrcView.LRCPLAYERSTATUS_PLAY) {
                        mManyLineLyricsView.play((int) audioMessage.getPlayProgress());
                    }
                }

            }


        } else if (action.equals(AudioBroadcastReceiver.ACTION_SERVICE_PAUSEMUSIC)) {
            //æš‚å?œå®Œæˆ?
            pauseImageView.setVisibility(View.INVISIBLE);
            playImageView.setVisibility(View.VISIBLE);

            if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                mManyLineLyricsView.pause();
            }

        } else if (action.equals(AudioBroadcastReceiver.ACTION_SERVICE_RESUMEMUSIC)) {
            //å”¤é†’å®Œæˆ?
            pauseImageView.setVisibility(View.VISIBLE);
            playImageView.setVisibility(View.INVISIBLE);

            AudioMessage audioMessage = mHPApplication.getCurAudioMessage();
            if (audioMessage != null) {
                if (mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.play((int) audioMessage.getPlayProgress());
                }
            }


        } else if (action.equals(AudioBroadcastReceiver.ACTION_SERVICE_SEEKTOMUSIC)) {
            //å”¤é†’å®Œæˆ?
            pauseImageView.setVisibility(View.VISIBLE);
            playImageView.setVisibility(View.INVISIBLE);
            AudioMessage audioMessage = mHPApplication.getCurAudioMessage();
            if (audioMessage != null) {
                if (mManyLineLyricsView != null && mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC) {
                    mManyLineLyricsView.play((int) audioMessage.getPlayProgress());
                }
            }
        }else if (action.equals(AudioBroadcastReceiver.ACTION_SERVICE_PLAYINGMUSIC)) {
            //æ’­æ”¾ä¸­
            AudioMessage audioMessage = mHPApplication.getCurAudioMessage();//(AudioMessage) intent.getSerializableExtra(AudioMessage.KEY);
            if (audioMessage != null) {

                playOrPauseButton.setPlayingProgress((int) audioMessage.getPlayProgress());
                playOrPauseButton.invalidate();

            }

        } else if (action.equals(AudioBroadcastReceiver.ACTION_LRCLOADED)) {

            //æ­Œè¯?åŠ è½½å®Œæˆ?
            AudioMessage curAudioMessage = mHPApplication.getCurAudioMessage();
            AudioMessage audioMessage = (AudioMessage) intent.getSerializableExtra(AudioMessage.KEY);
            String hash = audioMessage.getHash();
            if (hash.equals(mHPApplication.getCurAudioInfo().getHash())) {
                //
                LyricsReader lyricsReader = LyricsManager.getLyricsManager(mHPApplication, getApplicationContext()).getLyricsUtil(hash);
                if (lyricsReader != null) {
                    lyricsReader.setHash(hash);

                    mManyLineLyricsView.setLyricsReader(lyricsReader);
                    if (mHPApplication.getPlayStatus() == AudioPlayerManager.PLAYING && mManyLineLyricsView.getLrcStatus() == AbstractLrcView.LRCSTATUS_LRC && mManyLineLyricsView.getLrcPlayerStatus() != AbstractLrcView.LRCPLAYERSTATUS_PLAY)
                        mManyLineLyricsView.play((int) curAudioMessage.getPlayProgress());
                }
            }


        } else if (action.equals(AudioBroadcastReceiver.ACTION_RELOADSINGERIMG)) {

            //é‡?æ–°åŠ è½½æ­Œæ‰‹å†™çœŸ
            if (mHPApplication.getCurAudioInfo() != null) {
                String hash = intent.getStringExtra("hash");
                if (mHPApplication.getCurAudioInfo().getHash().equals(hash)) {
                    String singerName = intent.getStringExtra("singerName");
                    mSingerImageView.setVisibility(View.INVISIBLE);
                    mSingerImageView.setSongSingerInfos(mHPApplication, getApplicationContext(), null);
                    //åŠ è½½æ­Œæ‰‹å†™çœŸ
                    ImageUtil.loadSingerImg(mHPApplication, getApplicationContext(), hash, singerName);

                }
            }


        } else if (action.equals(AudioBroadcastReceiver.ACTION_SINGERIMGLOADED)) {
            //æ­Œæ‰‹å†™çœŸåŠ è½½å®Œæˆ?
            if (mHPApplication.getCurAudioInfo() != null) {
                String hash = intent.getStringExtra("hash");
                if (mHPApplication.getCurAudioInfo().getHash().equals(hash)) {
                    mSingerImageView.setVisibility(View.VISIBLE);

                    String singerName = intent.getStringExtra("singerName");
                    String[] singerNameArray = null;
                    if (singerName.contains("ã€?")) {

                        String regex = "\\s*ã€?\\s*";
                        singerNameArray = singerName.split(regex);


                    } else {
                        singerNameArray = new String[1];
                        singerNameArray[0] = singerName;
                    }


                    //è®¾ç½®æ•°æ?®
                    List<SongSingerInfo> list = SongSingerDB.getSongSingerDB(context).getAllSingerImg(singerNameArray, false);
                    mSingerImageView.setSongSingerInfos(mHPApplication, getApplicationContext(), list);
                }
            }
        }
    }


    @Override
    protected boolean isAddStatusBar() {
        setStatusColor(Color.TRANSPARENT);
        return true;
    }

    @Override
    public int setStatusBarParentView() {
        return R.id.status_parent_view;
    }

    @Override
    public void finish() {
        AniUtil.stopAnimation(aniLoading);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        //æ³¨é”€å¹¿æ’­
        mAudioBroadcastReceiver.unregisterReceiver(getApplicationContext());
        //æ³¨é”€åˆ†é’Ÿå?˜åŒ–å¹¿æ’­
        unregisterReceiver(mTimeReceiver);
        super.onDestroy();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) { // å±?è”½æŒ‰é”®
        if (keyCode == KeyEvent.KEYCODE_BACK
                || keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
