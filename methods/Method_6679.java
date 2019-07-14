public boolean playMessage(final MessageObject messageObject){
  if (messageObject == null) {
    return false;
  }
  if ((audioPlayer != null || videoPlayer != null) && isSamePlayingMessage(messageObject)) {
    if (isPaused) {
      resumeAudio(messageObject);
    }
    if (!SharedConfig.raiseToSpeak) {
      startRaiseToEarSensors(raiseChat);
    }
    return true;
  }
  if (!messageObject.isOut() && messageObject.isContentUnread()) {
    MessagesController.getInstance(messageObject.currentAccount).markMessageContentAsRead(messageObject);
  }
  boolean notify=!playMusicAgain;
  if (playingMessageObject != null) {
    notify=false;
    if (!playMusicAgain) {
      playingMessageObject.resetPlayingProgress();
    }
  }
  cleanupPlayer(notify,false);
  playMusicAgain=false;
  seekToProgressPending=0;
  File file=null;
  boolean exists=false;
  if (messageObject.messageOwner.attachPath != null && messageObject.messageOwner.attachPath.length() > 0) {
    file=new File(messageObject.messageOwner.attachPath);
    exists=file.exists();
    if (!exists) {
      file=null;
    }
  }
  final File cacheFile=file != null ? file : FileLoader.getPathToMessage(messageObject.messageOwner);
  boolean canStream=SharedConfig.streamMedia && (messageObject.isMusic() || messageObject.isRoundVideo() || messageObject.isVideo() && messageObject.canStreamVideo()) && (int)messageObject.getDialogId() != 0;
  if (cacheFile != null && cacheFile != file && !(exists=cacheFile.exists()) && !canStream) {
    FileLoader.getInstance(messageObject.currentAccount).loadFile(messageObject.getDocument(),messageObject,0,0);
    downloadingCurrentMessage=true;
    isPaused=false;
    lastProgress=0;
    audioInfo=null;
    playingMessageObject=messageObject;
    if (playingMessageObject.isMusic()) {
      Intent intent=new Intent(ApplicationLoader.applicationContext,MusicPlayerService.class);
      try {
        ApplicationLoader.applicationContext.startService(intent);
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
 else {
      Intent intent=new Intent(ApplicationLoader.applicationContext,MusicPlayerService.class);
      ApplicationLoader.applicationContext.stopService(intent);
    }
    NotificationCenter.getInstance(playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged,playingMessageObject.getId());
    return true;
  }
 else {
    downloadingCurrentMessage=false;
  }
  if (messageObject.isMusic()) {
    checkIsNextMusicFileDownloaded(messageObject.currentAccount);
  }
 else {
    checkIsNextVoiceFileDownloaded(messageObject.currentAccount);
  }
  if (currentAspectRatioFrameLayout != null) {
    isDrawingWasReady=false;
    currentAspectRatioFrameLayout.setDrawingReady(false);
  }
  boolean isVideo=messageObject.isVideo();
  if (messageObject.isRoundVideo() || isVideo) {
    FileLoader.getInstance(messageObject.currentAccount).setLoadingVideoForPlayer(messageObject.getDocument(),true);
    playerWasReady=false;
    boolean destroyAtEnd=!isVideo || messageObject.messageOwner.to_id.channel_id == 0 && messageObject.audioProgress <= 0.1f;
    int[] playCount=isVideo && messageObject.getDuration() <= 30 ? new int[]{1} : null;
    playlist.clear();
    shuffledPlaylist.clear();
    videoPlayer=new VideoPlayer();
    videoPlayer.setDelegate(new VideoPlayer.VideoPlayerDelegate(){
      @Override public void onStateChanged(      boolean playWhenReady,      int playbackState){
        updateVideoState(messageObject,playCount,destroyAtEnd,playWhenReady,playbackState);
      }
      @Override public void onError(      Exception e){
        FileLog.e(e);
      }
      @Override public void onVideoSizeChanged(      int width,      int height,      int unappliedRotationDegrees,      float pixelWidthHeightRatio){
        currentAspectRatioFrameLayoutRotation=unappliedRotationDegrees;
        if (unappliedRotationDegrees == 90 || unappliedRotationDegrees == 270) {
          int temp=width;
          width=height;
          height=temp;
        }
        currentAspectRatioFrameLayoutRatio=height == 0 ? 1 : (width * pixelWidthHeightRatio) / height;
        if (currentAspectRatioFrameLayout != null) {
          currentAspectRatioFrameLayout.setAspectRatio(currentAspectRatioFrameLayoutRatio,currentAspectRatioFrameLayoutRotation);
        }
      }
      @Override public void onRenderedFirstFrame(){
        if (currentAspectRatioFrameLayout != null && !currentAspectRatioFrameLayout.isDrawingReady()) {
          isDrawingWasReady=true;
          currentAspectRatioFrameLayout.setDrawingReady(true);
          currentTextureViewContainer.setTag(1);
        }
      }
      @Override public boolean onSurfaceDestroyed(      SurfaceTexture surfaceTexture){
        if (videoPlayer == null) {
          return false;
        }
        if (pipSwitchingState == 2) {
          if (currentAspectRatioFrameLayout != null) {
            if (isDrawingWasReady) {
              currentAspectRatioFrameLayout.setDrawingReady(true);
            }
            if (currentAspectRatioFrameLayout.getParent() == null) {
              currentTextureViewContainer.addView(currentAspectRatioFrameLayout);
            }
            if (currentTextureView.getSurfaceTexture() != surfaceTexture) {
              currentTextureView.setSurfaceTexture(surfaceTexture);
            }
            videoPlayer.setTextureView(currentTextureView);
          }
          pipSwitchingState=0;
          return true;
        }
 else         if (pipSwitchingState == 1) {
          if (baseActivity != null) {
            if (pipRoundVideoView == null) {
              try {
                pipRoundVideoView=new PipRoundVideoView();
                pipRoundVideoView.show(baseActivity,() -> cleanupPlayer(true,true));
              }
 catch (              Exception e) {
                pipRoundVideoView=null;
              }
            }
            if (pipRoundVideoView != null) {
              if (pipRoundVideoView.getTextureView().getSurfaceTexture() != surfaceTexture) {
                pipRoundVideoView.getTextureView().setSurfaceTexture(surfaceTexture);
              }
              videoPlayer.setTextureView(pipRoundVideoView.getTextureView());
            }
          }
          pipSwitchingState=0;
          return true;
        }
 else         if (PhotoViewer.hasInstance() && PhotoViewer.getInstance().isInjectingVideoPlayer()) {
          PhotoViewer.getInstance().injectVideoPlayerSurface(surfaceTexture);
          return true;
        }
        return false;
      }
      @Override public void onSurfaceTextureUpdated(      SurfaceTexture surfaceTexture){
      }
    }
);
    currentAspectRatioFrameLayoutReady=false;
    if (pipRoundVideoView != null || !MessagesController.getInstance(messageObject.currentAccount).isDialogVisible(messageObject.getDialogId())) {
      if (pipRoundVideoView == null) {
        try {
          pipRoundVideoView=new PipRoundVideoView();
          pipRoundVideoView.show(baseActivity,() -> cleanupPlayer(true,true));
        }
 catch (        Exception e) {
          pipRoundVideoView=null;
        }
      }
      if (pipRoundVideoView != null) {
        videoPlayer.setTextureView(pipRoundVideoView.getTextureView());
      }
    }
 else     if (currentTextureView != null) {
      videoPlayer.setTextureView(currentTextureView);
    }
    if (exists) {
      if (!messageObject.mediaExists && cacheFile != file) {
        AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.fileDidLoad,FileLoader.getAttachFileName(messageObject.getDocument())));
      }
      videoPlayer.preparePlayer(Uri.fromFile(cacheFile),"other");
    }
 else {
      try {
        int reference=FileLoader.getInstance(messageObject.currentAccount).getFileReference(messageObject);
        TLRPC.Document document=messageObject.getDocument();
        String params="?account=" + messageObject.currentAccount + "&id=" + document.id + "&hash=" + document.access_hash + "&dc=" + document.dc_id + "&size=" + document.size + "&mime=" + URLEncoder.encode(document.mime_type,"UTF-8") + "&rid=" + reference + "&name=" + URLEncoder.encode(FileLoader.getDocumentFileName(document),"UTF-8") + "&reference=" + Utilities.bytesToHex(document.file_reference != null ? document.file_reference : new byte[0]);
        Uri uri=Uri.parse("tg://" + messageObject.getFileName() + params);
        videoPlayer.preparePlayer(uri,"other");
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    if (messageObject.isRoundVideo()) {
      videoPlayer.setStreamType(useFrontSpeaker ? AudioManager.STREAM_VOICE_CALL : AudioManager.STREAM_MUSIC);
      if (currentPlaybackSpeed > 1.0f) {
        videoPlayer.setPlaybackSpeed(currentPlaybackSpeed);
      }
    }
 else {
      videoPlayer.setStreamType(AudioManager.STREAM_MUSIC);
    }
  }
 else {
    if (pipRoundVideoView != null) {
      pipRoundVideoView.close(true);
      pipRoundVideoView=null;
    }
    try {
      audioPlayer=new VideoPlayer();
      audioPlayer.setDelegate(new VideoPlayer.VideoPlayerDelegate(){
        @Override public void onStateChanged(        boolean playWhenReady,        int playbackState){
          if (playbackState == ExoPlayer.STATE_ENDED || (playbackState == ExoPlayer.STATE_IDLE || playbackState == ExoPlayer.STATE_BUFFERING) && playWhenReady && messageObject.audioProgress >= 0.999f) {
            if (!playlist.isEmpty() && playlist.size() > 1) {
              playNextMessageWithoutOrder(true);
            }
 else {
              cleanupPlayer(true,true,messageObject != null && messageObject.isVoice(),false);
            }
          }
 else           if (seekToProgressPending != 0 && (playbackState == ExoPlayer.STATE_READY || playbackState == ExoPlayer.STATE_IDLE)) {
            int seekTo=(int)(audioPlayer.getDuration() * seekToProgressPending);
            audioPlayer.seekTo(seekTo);
            lastProgress=seekTo;
            seekToProgressPending=0;
          }
        }
        @Override public void onError(        Exception e){
        }
        @Override public void onVideoSizeChanged(        int width,        int height,        int unappliedRotationDegrees,        float pixelWidthHeightRatio){
        }
        @Override public void onRenderedFirstFrame(){
        }
        @Override public void onSurfaceTextureUpdated(        SurfaceTexture surfaceTexture){
        }
        @Override public boolean onSurfaceDestroyed(        SurfaceTexture surfaceTexture){
          return false;
        }
      }
);
      if (exists) {
        if (!messageObject.mediaExists && cacheFile != file) {
          AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.fileDidLoad,FileLoader.getAttachFileName(messageObject.getDocument())));
        }
        audioPlayer.preparePlayer(Uri.fromFile(cacheFile),"other");
      }
 else {
        int reference=FileLoader.getInstance(messageObject.currentAccount).getFileReference(messageObject);
        TLRPC.Document document=messageObject.getDocument();
        String params="?account=" + messageObject.currentAccount + "&id=" + document.id + "&hash=" + document.access_hash + "&dc=" + document.dc_id + "&size=" + document.size + "&mime=" + URLEncoder.encode(document.mime_type,"UTF-8") + "&rid=" + reference + "&name=" + URLEncoder.encode(FileLoader.getDocumentFileName(document),"UTF-8") + "&reference=" + Utilities.bytesToHex(document.file_reference != null ? document.file_reference : new byte[0]);
        Uri uri=Uri.parse("tg://" + messageObject.getFileName() + params);
        audioPlayer.preparePlayer(uri,"other");
      }
      if (messageObject.isVoice()) {
        if (currentPlaybackSpeed > 1.0f) {
          audioPlayer.setPlaybackSpeed(currentPlaybackSpeed);
        }
        audioInfo=null;
        playlist.clear();
        shuffledPlaylist.clear();
      }
 else {
        try {
          audioInfo=AudioInfo.getAudioInfo(cacheFile);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      audioPlayer.setStreamType(useFrontSpeaker ? AudioManager.STREAM_VOICE_CALL : AudioManager.STREAM_MUSIC);
      audioPlayer.play();
    }
 catch (    Exception e) {
      FileLog.e(e);
      NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged,playingMessageObject != null ? playingMessageObject.getId() : 0);
      if (audioPlayer != null) {
        audioPlayer.releasePlayer(true);
        audioPlayer=null;
        isPaused=false;
        playingMessageObject=null;
        downloadingCurrentMessage=false;
      }
      return false;
    }
  }
  checkAudioFocus(messageObject);
  setPlayerVolume();
  isPaused=false;
  lastProgress=0;
  playingMessageObject=messageObject;
  if (!SharedConfig.raiseToSpeak) {
    startRaiseToEarSensors(raiseChat);
  }
  startProgressTimer(playingMessageObject);
  NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidStart,messageObject);
  if (videoPlayer != null) {
    try {
      if (playingMessageObject.audioProgress != 0) {
        long duration=videoPlayer.getDuration();
        if (duration == C.TIME_UNSET) {
          duration=(long)playingMessageObject.getDuration() * 1000;
        }
        int seekTo=(int)(duration * playingMessageObject.audioProgress);
        if (playingMessageObject.audioProgressMs != 0) {
          seekTo=playingMessageObject.audioProgressMs;
          playingMessageObject.audioProgressMs=0;
        }
        videoPlayer.seekTo(seekTo);
      }
    }
 catch (    Exception e2) {
      playingMessageObject.audioProgress=0;
      playingMessageObject.audioProgressSec=0;
      NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged,playingMessageObject.getId(),0);
      FileLog.e(e2);
    }
    videoPlayer.play();
  }
 else   if (audioPlayer != null) {
    try {
      if (playingMessageObject.audioProgress != 0) {
        long duration=audioPlayer.getDuration();
        if (duration == C.TIME_UNSET) {
          duration=(long)playingMessageObject.getDuration() * 1000;
        }
        int seekTo=(int)(duration * playingMessageObject.audioProgress);
        audioPlayer.seekTo(seekTo);
      }
    }
 catch (    Exception e2) {
      playingMessageObject.resetPlayingProgress();
      NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged,playingMessageObject.getId(),0);
      FileLog.e(e2);
    }
  }
  if (playingMessageObject != null && playingMessageObject.isMusic()) {
    Intent intent=new Intent(ApplicationLoader.applicationContext,MusicPlayerService.class);
    try {
      ApplicationLoader.applicationContext.startService(intent);
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
 else {
    Intent intent=new Intent(ApplicationLoader.applicationContext,MusicPlayerService.class);
    ApplicationLoader.applicationContext.stopService(intent);
  }
  return true;
}
