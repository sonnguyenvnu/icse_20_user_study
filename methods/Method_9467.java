private void createVideoControlsInterface(){
  videoPlayerSeekbar=new SeekBar(containerView.getContext());
  videoPlayerSeekbar.setLineHeight(AndroidUtilities.dp(4));
  videoPlayerSeekbar.setColors(0x66ffffff,0x66ffffff,0xffd5d0d7,0xffffffff,0xffffffff);
  videoPlayerSeekbar.setDelegate(progress -> {
    if (videoPlayer != null) {
      if (!inPreview && videoTimelineView.getVisibility() == View.VISIBLE) {
        progress=videoTimelineView.getLeftProgress() + (videoTimelineView.getRightProgress() - videoTimelineView.getLeftProgress()) * progress;
      }
      long duration=videoPlayer.getDuration();
      if (duration == C.TIME_UNSET) {
        seekToProgressPending=progress;
      }
 else {
        videoPlayer.seekTo((int)(progress * duration));
      }
    }
  }
);
  videoPlayerControlFrameLayout=new FrameLayout(containerView.getContext()){
    @Override public boolean onTouchEvent(    MotionEvent event){
      int x=(int)event.getX();
      int y=(int)event.getY();
      if (videoPlayerSeekbar.onTouch(event.getAction(),event.getX() - AndroidUtilities.dp(48),event.getY())) {
        getParent().requestDisallowInterceptTouchEvent(true);
        invalidate();
        return true;
      }
      return true;
    }
    @Override protected void onMeasure(    int widthMeasureSpec,    int heightMeasureSpec){
      super.onMeasure(widthMeasureSpec,heightMeasureSpec);
      long duration;
      if (videoPlayer != null) {
        duration=videoPlayer.getDuration();
        if (duration == C.TIME_UNSET) {
          duration=0;
        }
      }
 else {
        duration=0;
      }
      duration/=1000;
      int size=(int)Math.ceil(videoPlayerTime.getPaint().measureText(String.format("%02d:%02d / %02d:%02d",duration / 60,duration % 60,duration / 60,duration % 60)));
      videoPlayerSeekbar.setSize(getMeasuredWidth() - AndroidUtilities.dp(48 + 16) - size,getMeasuredHeight());
    }
    @Override protected void onLayout(    boolean changed,    int left,    int top,    int right,    int bottom){
      super.onLayout(changed,left,top,right,bottom);
      float progress=0;
      if (videoPlayer != null) {
        progress=videoPlayer.getCurrentPosition() / (float)videoPlayer.getDuration();
        if (!inPreview && videoTimelineView.getVisibility() == View.VISIBLE) {
          progress-=videoTimelineView.getLeftProgress();
          if (progress < 0) {
            progress=0;
          }
          progress/=(videoTimelineView.getRightProgress() - videoTimelineView.getLeftProgress());
          if (progress > 1) {
            progress=1;
          }
        }
      }
      videoPlayerSeekbar.setProgress(progress);
      videoTimelineView.setProgress(progress);
    }
    @Override protected void onDraw(    Canvas canvas){
      canvas.save();
      canvas.translate(AndroidUtilities.dp(48),0);
      videoPlayerSeekbar.draw(canvas);
      canvas.restore();
    }
  }
;
  videoPlayerControlFrameLayout.setWillNotDraw(false);
  bottomLayout.addView(videoPlayerControlFrameLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
  videoPlayButton=new ImageView(containerView.getContext());
  videoPlayButton.setScaleType(ImageView.ScaleType.CENTER);
  videoPlayerControlFrameLayout.addView(videoPlayButton,LayoutHelper.createFrame(48,48,Gravity.LEFT | Gravity.TOP,4,0,0,0));
  videoPlayButton.setFocusable(true);
  videoPlayButton.setContentDescription(LocaleController.getString("AccActionPlay",R.string.AccActionPlay));
  videoPlayButton.setOnClickListener(v -> {
    if (videoPlayer == null) {
      return;
    }
    if (isPlaying) {
      videoPlayer.pause();
    }
 else {
      if (isCurrentVideo) {
        if (Math.abs(videoTimelineView.getProgress() - 1.0f) < 0.01f || videoPlayer.getCurrentPosition() == videoPlayer.getDuration()) {
          videoPlayer.seekTo(0);
        }
      }
 else {
        if (Math.abs(videoPlayerSeekbar.getProgress() - 1.0f) < 0.01f || videoPlayer.getCurrentPosition() == videoPlayer.getDuration()) {
          videoPlayer.seekTo(0);
        }
      }
      videoPlayer.play();
    }
    containerView.invalidate();
  }
);
  videoPlayerTime=new SimpleTextView(containerView.getContext());
  videoPlayerTime.setTextColor(0xffffffff);
  videoPlayerTime.setGravity(Gravity.RIGHT | Gravity.TOP);
  videoPlayerTime.setTextSize(13);
  videoPlayerControlFrameLayout.addView(videoPlayerTime,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.RIGHT | Gravity.TOP,0,17,7,0));
}
