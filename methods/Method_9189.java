public boolean loadVideo(String url,TLRPC.Photo thumb,Object parentObject,String originalUrl,boolean autoplay){
  String youtubeId=null;
  String vimeoId=null;
  String coubId=null;
  String twitchClipId=null;
  String twitchStreamId=null;
  String mp4File=null;
  String aparatId=null;
  seekToTime=-1;
  if (url != null) {
    if (url.endsWith(".mp4")) {
      mp4File=url;
    }
 else {
      try {
        if (originalUrl != null) {
          try {
            Uri uri=Uri.parse(originalUrl);
            String t=uri.getQueryParameter("t");
            if (t == null) {
              t=uri.getQueryParameter("time_continue");
            }
            if (t != null) {
              if (t.contains("m")) {
                String args[]=t.split("m");
                seekToTime=Utilities.parseInt(args[0]) * 60 + Utilities.parseInt(args[1]);
              }
 else {
                seekToTime=Utilities.parseInt(t);
              }
            }
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        Matcher matcher=youtubeIdRegex.matcher(url);
        String id=null;
        if (matcher.find()) {
          id=matcher.group(1);
        }
        if (id != null) {
          youtubeId=id;
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      if (youtubeId == null) {
        try {
          Matcher matcher=vimeoIdRegex.matcher(url);
          String id=null;
          if (matcher.find()) {
            id=matcher.group(3);
          }
          if (id != null) {
            vimeoId=id;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (vimeoId == null) {
        try {
          Matcher matcher=aparatIdRegex.matcher(url);
          String id=null;
          if (matcher.find()) {
            id=matcher.group(1);
          }
          if (id != null) {
            aparatId=id;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (aparatId == null) {
        try {
          Matcher matcher=twitchClipIdRegex.matcher(url);
          String id=null;
          if (matcher.find()) {
            id=matcher.group(1);
          }
          if (id != null) {
            twitchClipId=id;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (twitchClipId == null) {
        try {
          Matcher matcher=twitchStreamIdRegex.matcher(url);
          String id=null;
          if (matcher.find()) {
            id=matcher.group(1);
          }
          if (id != null) {
            twitchStreamId=id;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
      if (twitchStreamId == null) {
        try {
          Matcher matcher=coubIdRegex.matcher(url);
          String id=null;
          if (matcher.find()) {
            id=matcher.group(1);
          }
          if (id != null) {
            coubId=id;
          }
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
    }
  }
  initied=false;
  isCompleted=false;
  isAutoplay=autoplay;
  playVideoUrl=null;
  playAudioUrl=null;
  destroy();
  firstFrameRendered=false;
  currentAlpha=1.0f;
  if (currentTask != null) {
    currentTask.cancel(true);
    currentTask=null;
  }
  updateFullscreenButton();
  updateShareButton();
  updateInlineButton();
  updatePlayButton();
  if (thumb != null) {
    TLRPC.PhotoSize photoSize=FileLoader.getClosestPhotoSizeWithSize(thumb.sizes,80,true);
    if (photoSize != null) {
      controlsView.imageReceiver.setImage(null,null,ImageLocation.getForPhoto(photoSize,thumb),"80_80_b",0,null,parentObject,1);
      drawImage=true;
    }
  }
 else {
    drawImage=false;
  }
  if (progressAnimation != null) {
    progressAnimation.cancel();
    progressAnimation=null;
  }
  isLoading=true;
  controlsView.setProgress(0);
  if (youtubeId != null) {
    currentYoutubeId=youtubeId;
    youtubeId=null;
  }
  if (mp4File != null) {
    initied=true;
    playVideoUrl=mp4File;
    playVideoType="other";
    if (isAutoplay) {
      preparePlayer();
    }
    showProgress(false,false);
    controlsView.show(true,true);
  }
 else {
    if (youtubeId != null) {
      YoutubeVideoTask task=new YoutubeVideoTask(youtubeId);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
    }
 else     if (vimeoId != null) {
      VimeoVideoTask task=new VimeoVideoTask(vimeoId);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
    }
 else     if (coubId != null) {
      CoubVideoTask task=new CoubVideoTask(coubId);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
      isStream=true;
    }
 else     if (aparatId != null) {
      AparatVideoTask task=new AparatVideoTask(aparatId);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
    }
 else     if (twitchClipId != null) {
      TwitchClipVideoTask task=new TwitchClipVideoTask(url,twitchClipId);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
    }
 else     if (twitchStreamId != null) {
      TwitchStreamVideoTask task=new TwitchStreamVideoTask(url,twitchStreamId);
      task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null,null,null);
      currentTask=task;
      isStream=true;
    }
    controlsView.show(false,false);
    showProgress(true,false);
  }
  if (youtubeId != null || vimeoId != null || coubId != null || aparatId != null || mp4File != null || twitchClipId != null || twitchStreamId != null) {
    controlsView.setVisibility(VISIBLE);
    return true;
  }
  controlsView.setVisibility(GONE);
  return false;
}
