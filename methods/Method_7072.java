@SuppressLint("NewApi") private void createNotification(MessageObject messageObject,boolean forBitmap){
  String songName=messageObject.getMusicTitle();
  String authorName=messageObject.getMusicAuthor();
  AudioInfo audioInfo=MediaController.getInstance().getAudioInfo();
  Intent intent=new Intent(ApplicationLoader.applicationContext,LaunchActivity.class);
  intent.setAction("com.tmessages.openplayer");
  intent.addCategory(Intent.CATEGORY_LAUNCHER);
  PendingIntent contentIntent=PendingIntent.getActivity(ApplicationLoader.applicationContext,0,intent,0);
  Notification notification;
  String artworkUrl=messageObject.getArtworkUrl(true);
  String artworkUrlBig=messageObject.getArtworkUrl(false);
  long duration=messageObject.getDuration() * 1000;
  Bitmap albumArt=audioInfo != null ? audioInfo.getSmallCover() : null;
  Bitmap fullAlbumArt=audioInfo != null ? audioInfo.getCover() : null;
  loadingFilePath=null;
  imageReceiver.setImageBitmap((BitmapDrawable)null);
  if (albumArt == null && !TextUtils.isEmpty(artworkUrl)) {
    fullAlbumArt=loadArtworkFromUrl(artworkUrlBig,true,!forBitmap);
    if (fullAlbumArt == null) {
      fullAlbumArt=albumArt=loadArtworkFromUrl(artworkUrl,false,!forBitmap);
    }
 else {
      albumArt=loadArtworkFromUrl(artworkUrlBig,false,!forBitmap);
    }
  }
 else {
    loadingFilePath=FileLoader.getPathToAttach(messageObject.getDocument()).getAbsolutePath();
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    boolean isPlaying=!MediaController.getInstance().isMessagePaused();
    PendingIntent pendingPrev=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_PREVIOUS).setComponent(new ComponentName(this,MusicPlayerReceiver.class)),PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent pendingStop=PendingIntent.getService(getApplicationContext(),0,new Intent(this,getClass()).setAction(getPackageName() + ".STOP_PLAYER"),PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent pendingPlaypause=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(isPlaying ? NOTIFY_PAUSE : NOTIFY_PLAY).setComponent(new ComponentName(this,MusicPlayerReceiver.class)),PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent pendingNext=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_NEXT).setComponent(new ComponentName(this,MusicPlayerReceiver.class)),PendingIntent.FLAG_CANCEL_CURRENT);
    PendingIntent pendingSeek=PendingIntent.getBroadcast(getApplicationContext(),0,new Intent(NOTIFY_SEEK).setComponent(new ComponentName(this,MusicPlayerReceiver.class)),PendingIntent.FLAG_CANCEL_CURRENT);
    Notification.Builder bldr=new Notification.Builder(this);
    bldr.setSmallIcon(R.drawable.player).setOngoing(isPlaying).setContentTitle(songName).setContentText(authorName).setSubText(audioInfo != null ? audioInfo.getAlbum() : null).setContentIntent(contentIntent).setDeleteIntent(pendingStop).setShowWhen(false).setCategory(Notification.CATEGORY_TRANSPORT).setPriority(Notification.PRIORITY_MAX).setStyle(new Notification.MediaStyle().setMediaSession(mediaSession.getSessionToken()).setShowActionsInCompactView(0,1,2));
    if (Build.VERSION.SDK_INT >= 26) {
      NotificationsController.checkOtherNotificationsChannel();
      bldr.setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL);
    }
    if (albumArt != null) {
      bldr.setLargeIcon(albumArt);
    }
 else {
      bldr.setLargeIcon(albumArtPlaceholder);
    }
    if (MediaController.getInstance().isDownloadingCurrentMessage()) {
      playbackState.setState(PlaybackState.STATE_BUFFERING,0,1).setActions(0);
      bldr.addAction(new Notification.Action.Builder(R.drawable.ic_action_previous,"",pendingPrev).build()).addAction(new Notification.Action.Builder(R.drawable.loading_animation2,"",null).build()).addAction(new Notification.Action.Builder(R.drawable.ic_action_next,"",pendingNext).build());
    }
 else {
      playbackState.setState(isPlaying ? PlaybackState.STATE_PLAYING : PlaybackState.STATE_PAUSED,MediaController.getInstance().getPlayingMessageObject().audioProgressSec * 1000L,isPlaying ? 1 : 0).setActions(PlaybackState.ACTION_PLAY_PAUSE | PlaybackState.ACTION_PLAY | PlaybackState.ACTION_PAUSE | PlaybackState.ACTION_SEEK_TO | PlaybackState.ACTION_SKIP_TO_PREVIOUS | PlaybackState.ACTION_SKIP_TO_NEXT);
      bldr.addAction(new Notification.Action.Builder(R.drawable.ic_action_previous,"",pendingPrev).build()).addAction(new Notification.Action.Builder(isPlaying ? R.drawable.ic_action_pause : R.drawable.ic_action_play,"",pendingPlaypause).build()).addAction(new Notification.Action.Builder(R.drawable.ic_action_next,"",pendingNext).build());
    }
    mediaSession.setPlaybackState(playbackState.build());
    MediaMetadata.Builder meta=new MediaMetadata.Builder().putBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART,fullAlbumArt).putString(MediaMetadata.METADATA_KEY_ALBUM_ARTIST,authorName).putLong(MediaMetadata.METADATA_KEY_DURATION,duration).putString(MediaMetadata.METADATA_KEY_TITLE,songName).putString(MediaMetadata.METADATA_KEY_ALBUM,audioInfo != null ? audioInfo.getAlbum() : null);
    mediaSession.setMetadata(meta.build());
    bldr.setVisibility(Notification.VISIBILITY_PUBLIC);
    notification=bldr.build();
    if (isPlaying) {
      startForeground(ID_NOTIFICATION,notification);
    }
 else {
      stopForeground(false);
      NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
      nm.notify(ID_NOTIFICATION,notification);
    }
  }
 else {
    RemoteViews simpleContentView=new RemoteViews(getApplicationContext().getPackageName(),R.layout.player_small_notification);
    RemoteViews expandedView=null;
    if (supportBigNotifications) {
      expandedView=new RemoteViews(getApplicationContext().getPackageName(),R.layout.player_big_notification);
    }
    notification=new NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.drawable.player).setContentIntent(contentIntent).setChannelId(NotificationsController.OTHER_NOTIFICATIONS_CHANNEL).setContentTitle(songName).build();
    notification.contentView=simpleContentView;
    if (supportBigNotifications) {
      notification.bigContentView=expandedView;
    }
    setListeners(simpleContentView);
    if (supportBigNotifications) {
      setListeners(expandedView);
    }
    if (albumArt != null) {
      notification.contentView.setImageViewBitmap(R.id.player_album_art,albumArt);
      if (supportBigNotifications) {
        notification.bigContentView.setImageViewBitmap(R.id.player_album_art,albumArt);
      }
    }
 else {
      notification.contentView.setImageViewResource(R.id.player_album_art,R.drawable.nocover_small);
      if (supportBigNotifications) {
        notification.bigContentView.setImageViewResource(R.id.player_album_art,R.drawable.nocover_big);
      }
    }
    if (MediaController.getInstance().isDownloadingCurrentMessage()) {
      notification.contentView.setViewVisibility(R.id.player_pause,View.GONE);
      notification.contentView.setViewVisibility(R.id.player_play,View.GONE);
      notification.contentView.setViewVisibility(R.id.player_next,View.GONE);
      notification.contentView.setViewVisibility(R.id.player_previous,View.GONE);
      notification.contentView.setViewVisibility(R.id.player_progress_bar,View.VISIBLE);
      if (supportBigNotifications) {
        notification.bigContentView.setViewVisibility(R.id.player_pause,View.GONE);
        notification.bigContentView.setViewVisibility(R.id.player_play,View.GONE);
        notification.bigContentView.setViewVisibility(R.id.player_next,View.GONE);
        notification.bigContentView.setViewVisibility(R.id.player_previous,View.GONE);
        notification.bigContentView.setViewVisibility(R.id.player_progress_bar,View.VISIBLE);
      }
    }
 else {
      notification.contentView.setViewVisibility(R.id.player_progress_bar,View.GONE);
      notification.contentView.setViewVisibility(R.id.player_next,View.VISIBLE);
      notification.contentView.setViewVisibility(R.id.player_previous,View.VISIBLE);
      if (supportBigNotifications) {
        notification.bigContentView.setViewVisibility(R.id.player_next,View.VISIBLE);
        notification.bigContentView.setViewVisibility(R.id.player_previous,View.VISIBLE);
        notification.bigContentView.setViewVisibility(R.id.player_progress_bar,View.GONE);
      }
      if (MediaController.getInstance().isMessagePaused()) {
        notification.contentView.setViewVisibility(R.id.player_pause,View.GONE);
        notification.contentView.setViewVisibility(R.id.player_play,View.VISIBLE);
        if (supportBigNotifications) {
          notification.bigContentView.setViewVisibility(R.id.player_pause,View.GONE);
          notification.bigContentView.setViewVisibility(R.id.player_play,View.VISIBLE);
        }
      }
 else {
        notification.contentView.setViewVisibility(R.id.player_pause,View.VISIBLE);
        notification.contentView.setViewVisibility(R.id.player_play,View.GONE);
        if (supportBigNotifications) {
          notification.bigContentView.setViewVisibility(R.id.player_pause,View.VISIBLE);
          notification.bigContentView.setViewVisibility(R.id.player_play,View.GONE);
        }
      }
    }
    notification.contentView.setTextViewText(R.id.player_song_name,songName);
    notification.contentView.setTextViewText(R.id.player_author_name,authorName);
    if (supportBigNotifications) {
      notification.bigContentView.setTextViewText(R.id.player_song_name,songName);
      notification.bigContentView.setTextViewText(R.id.player_author_name,authorName);
      notification.bigContentView.setTextViewText(R.id.player_album_title,audioInfo != null && !TextUtils.isEmpty(audioInfo.getAlbum()) ? audioInfo.getAlbum() : "");
    }
    notification.flags|=Notification.FLAG_ONGOING_EVENT;
    startForeground(ID_NOTIFICATION,notification);
  }
  if (remoteControlClient != null) {
    int currentID=MediaController.getInstance().getPlayingMessageObject().getId();
    if (notificationMessageID != currentID) {
      notificationMessageID=currentID;
      RemoteControlClient.MetadataEditor metadataEditor=remoteControlClient.editMetadata(true);
      metadataEditor.putString(MediaMetadataRetriever.METADATA_KEY_ARTIST,authorName);
      metadataEditor.putString(MediaMetadataRetriever.METADATA_KEY_TITLE,songName);
      if (audioInfo != null && !TextUtils.isEmpty(audioInfo.getAlbum())) {
        metadataEditor.putString(MediaMetadataRetriever.METADATA_KEY_ALBUM,audioInfo.getAlbum());
      }
      metadataEditor.putLong(MediaMetadataRetriever.METADATA_KEY_DURATION,MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration * 1000L);
      if (fullAlbumArt != null) {
        try {
          metadataEditor.putBitmap(RemoteControlClient.MetadataEditor.BITMAP_KEY_ARTWORK,fullAlbumArt);
        }
 catch (        Throwable e) {
          FileLog.e(e);
        }
      }
      metadataEditor.apply();
      AndroidUtilities.runOnUIThread(new Runnable(){
        @Override public void run(){
          if (remoteControlClient == null || MediaController.getInstance().getPlayingMessageObject() == null) {
            return;
          }
          if (MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration == C.TIME_UNSET) {
            AndroidUtilities.runOnUIThread(this,500);
            return;
          }
          RemoteControlClient.MetadataEditor metadataEditor=remoteControlClient.editMetadata(false);
          metadataEditor.putLong(MediaMetadataRetriever.METADATA_KEY_DURATION,MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration * 1000L);
          metadataEditor.apply();
          if (Build.VERSION.SDK_INT >= 18) {
            remoteControlClient.setPlaybackState(MediaController.getInstance().isMessagePaused() ? RemoteControlClient.PLAYSTATE_PAUSED : RemoteControlClient.PLAYSTATE_PLAYING,Math.max(MediaController.getInstance().getPlayingMessageObject().audioProgressSec * 1000L,100),MediaController.getInstance().isMessagePaused() ? 0f : 1f);
          }
 else {
            remoteControlClient.setPlaybackState(MediaController.getInstance().isMessagePaused() ? RemoteControlClient.PLAYSTATE_PAUSED : RemoteControlClient.PLAYSTATE_PLAYING);
          }
        }
      }
,1000);
    }
    if (MediaController.getInstance().isDownloadingCurrentMessage()) {
      remoteControlClient.setPlaybackState(RemoteControlClient.PLAYSTATE_BUFFERING);
    }
 else {
      RemoteControlClient.MetadataEditor metadataEditor=remoteControlClient.editMetadata(false);
      metadataEditor.putLong(MediaMetadataRetriever.METADATA_KEY_DURATION,MediaController.getInstance().getPlayingMessageObject().audioPlayerDuration * 1000L);
      metadataEditor.apply();
      if (Build.VERSION.SDK_INT >= 18) {
        remoteControlClient.setPlaybackState(MediaController.getInstance().isMessagePaused() ? RemoteControlClient.PLAYSTATE_PAUSED : RemoteControlClient.PLAYSTATE_PLAYING,Math.max(MediaController.getInstance().getPlayingMessageObject().audioProgressSec * 1000L,100),MediaController.getInstance().isMessagePaused() ? 0f : 1f);
      }
 else {
        remoteControlClient.setPlaybackState(MediaController.getInstance().isMessagePaused() ? RemoteControlClient.PLAYSTATE_PAUSED : RemoteControlClient.PLAYSTATE_PLAYING);
      }
    }
  }
}
