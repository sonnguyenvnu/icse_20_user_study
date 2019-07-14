private void startProgressTimer(final MessageObject currentPlayingMessageObject){
synchronized (progressTimerSync) {
    if (progressTimer != null) {
      try {
        progressTimer.cancel();
        progressTimer=null;
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    final String fileName=currentPlayingMessageObject.getFileName();
    progressTimer=new Timer();
    progressTimer.schedule(new TimerTask(){
      @Override public void run(){
synchronized (sync) {
          AndroidUtilities.runOnUIThread(() -> {
            if (currentPlayingMessageObject != null && (audioPlayer != null || videoPlayer != null) && !isPaused) {
              try {
                long duration;
                long progress;
                float value;
                float bufferedValue;
                if (videoPlayer != null) {
                  duration=videoPlayer.getDuration();
                  progress=videoPlayer.getCurrentPosition();
                  if (duration == C.TIME_UNSET || progress == C.TIME_UNSET || progress < 0 || duration <= 0) {
                    return;
                  }
                  bufferedValue=videoPlayer.getBufferedPosition() / (float)duration;
                  value=duration >= 0 ? progress / (float)duration : 0.0f;
                  if (value >= 1) {
                    return;
                  }
                }
 else {
                  duration=audioPlayer.getDuration();
                  progress=audioPlayer.getCurrentPosition();
                  value=duration != C.TIME_UNSET && duration >= 0 ? (progress / (float)duration) : 0.0f;
                  bufferedValue=audioPlayer.getBufferedPosition() / (float)duration;
                  if (duration == C.TIME_UNSET || progress < 0 || seekToProgressPending != 0) {
                    return;
                  }
                }
                lastProgress=progress;
                currentPlayingMessageObject.audioPlayerDuration=(int)(duration / 1000);
                currentPlayingMessageObject.audioProgress=value;
                currentPlayingMessageObject.audioProgressSec=(int)(lastProgress / 1000);
                currentPlayingMessageObject.bufferedProgress=bufferedValue;
                NotificationCenter.getInstance(currentPlayingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged,currentPlayingMessageObject.getId(),value);
              }
 catch (              Exception e) {
                FileLog.e(e);
              }
            }
          }
);
        }
      }
    }
,0,17);
  }
}
