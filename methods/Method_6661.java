public void cleanupPlayer(boolean notify,boolean stopService,boolean byVoiceEnd,boolean transferPlayerToPhotoViewer){
  if (audioPlayer != null) {
    try {
      audioPlayer.releasePlayer(true);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    audioPlayer=null;
  }
 else   if (videoPlayer != null) {
    currentAspectRatioFrameLayout=null;
    currentTextureViewContainer=null;
    currentAspectRatioFrameLayoutReady=false;
    isDrawingWasReady=false;
    currentTextureView=null;
    goingToShowMessageObject=null;
    if (transferPlayerToPhotoViewer) {
      PhotoViewer.getInstance().injectVideoPlayer(videoPlayer);
      goingToShowMessageObject=playingMessageObject;
      NotificationCenter.getInstance(playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingGoingToStop,playingMessageObject,true);
    }
 else {
      long position=videoPlayer.getCurrentPosition();
      if (playingMessageObject != null && playingMessageObject.isVideo() && position > 0 && position != C.TIME_UNSET) {
        playingMessageObject.audioProgressMs=(int)position;
        NotificationCenter.getInstance(playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingGoingToStop,playingMessageObject,false);
      }
      videoPlayer.releasePlayer(true);
      videoPlayer=null;
    }
    try {
      baseActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    if (playingMessageObject != null && !transferPlayerToPhotoViewer) {
      AndroidUtilities.cancelRunOnUIThread(setLoadingRunnable);
      FileLoader.getInstance(playingMessageObject.currentAccount).removeLoadingVideo(playingMessageObject.getDocument(),true,false);
    }
  }
  stopProgressTimer();
  lastProgress=0;
  isPaused=false;
  if (!useFrontSpeaker && !SharedConfig.raiseToSpeak) {
    ChatActivity chat=raiseChat;
    stopRaiseToEarSensors(raiseChat,false);
    raiseChat=chat;
  }
  if (playingMessageObject != null) {
    if (downloadingCurrentMessage) {
      FileLoader.getInstance(playingMessageObject.currentAccount).cancelLoadFile(playingMessageObject.getDocument());
    }
    MessageObject lastFile=playingMessageObject;
    if (notify) {
      playingMessageObject.resetPlayingProgress();
      NotificationCenter.getInstance(lastFile.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged,playingMessageObject.getId(),0);
    }
    playingMessageObject=null;
    downloadingCurrentMessage=false;
    if (notify) {
      NotificationsController.audioManager.abandonAudioFocus(this);
      hasAudioFocus=0;
      int index=-1;
      if (voiceMessagesPlaylist != null) {
        if (byVoiceEnd && (index=voiceMessagesPlaylist.indexOf(lastFile)) >= 0) {
          voiceMessagesPlaylist.remove(index);
          voiceMessagesPlaylistMap.remove(lastFile.getId());
          if (voiceMessagesPlaylist.isEmpty()) {
            voiceMessagesPlaylist=null;
            voiceMessagesPlaylistMap=null;
          }
        }
 else {
          voiceMessagesPlaylist=null;
          voiceMessagesPlaylistMap=null;
        }
      }
      boolean next=false;
      if (voiceMessagesPlaylist != null && index < voiceMessagesPlaylist.size()) {
        MessageObject nextVoiceMessage=voiceMessagesPlaylist.get(index);
        playMessage(nextVoiceMessage);
        if (!nextVoiceMessage.isRoundVideo() && pipRoundVideoView != null) {
          pipRoundVideoView.close(true);
          pipRoundVideoView=null;
        }
      }
 else {
        if ((lastFile.isVoice() || lastFile.isRoundVideo()) && lastFile.getId() != 0) {
          startRecordingIfFromSpeaker();
        }
        NotificationCenter.getInstance(lastFile.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidReset,lastFile.getId(),stopService);
        pipSwitchingState=0;
        if (pipRoundVideoView != null) {
          pipRoundVideoView.close(true);
          pipRoundVideoView=null;
        }
      }
    }
    if (stopService) {
      Intent intent=new Intent(ApplicationLoader.applicationContext,MusicPlayerService.class);
      ApplicationLoader.applicationContext.stopService(intent);
    }
  }
}
