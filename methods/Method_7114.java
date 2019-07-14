private void showOrUpdateNotification(boolean notifyAboutLast){
  if (!UserConfig.getInstance(currentAccount).isClientActivated() || pushMessages.isEmpty() || !SharedConfig.showNotificationsForAllAccounts && currentAccount != UserConfig.selectedAccount) {
    dismissNotification();
    return;
  }
  try {
    ConnectionsManager.getInstance(currentAccount).resumeNetworkMaybe();
    MessageObject lastMessageObject=pushMessages.get(0);
    SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
    int dismissDate=preferences.getInt("dismissDate",0);
    if (lastMessageObject.messageOwner.date <= dismissDate) {
      dismissNotification();
      return;
    }
    long dialog_id=lastMessageObject.getDialogId();
    boolean isChannel=false;
    long override_dialog_id=dialog_id;
    if (lastMessageObject.messageOwner.mentioned) {
      override_dialog_id=lastMessageObject.messageOwner.from_id;
    }
    int mid=lastMessageObject.getId();
    int chat_id=lastMessageObject.messageOwner.to_id.chat_id != 0 ? lastMessageObject.messageOwner.to_id.chat_id : lastMessageObject.messageOwner.to_id.channel_id;
    int user_id=lastMessageObject.messageOwner.to_id.user_id;
    if (user_id == 0) {
      user_id=lastMessageObject.messageOwner.from_id;
    }
 else     if (user_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
      user_id=lastMessageObject.messageOwner.from_id;
    }
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
    TLRPC.Chat chat=null;
    if (chat_id != 0) {
      chat=MessagesController.getInstance(currentAccount).getChat(chat_id);
      isChannel=ChatObject.isChannel(chat) && !chat.megagroup;
    }
    TLRPC.FileLocation photoPath=null;
    boolean notifyDisabled=false;
    int needVibrate=0;
    String choosenSoundPath;
    int ledColor=0xff0000ff;
    int priority=0;
    int notifyOverride=getNotifyOverride(preferences,override_dialog_id);
    boolean value;
    if (notifyOverride == -1) {
      value=isGlobalNotificationsEnabled(dialog_id);
    }
 else {
      value=notifyOverride != 2;
    }
    if (!notifyAboutLast || !value) {
      notifyDisabled=true;
    }
    if (!notifyDisabled && dialog_id == override_dialog_id && chat != null) {
      int notifyMaxCount;
      int notifyDelay;
      if (preferences.getBoolean("custom_" + dialog_id,false)) {
        notifyMaxCount=preferences.getInt("smart_max_count_" + dialog_id,2);
        notifyDelay=preferences.getInt("smart_delay_" + dialog_id,3 * 60);
      }
 else {
        notifyMaxCount=2;
        notifyDelay=3 * 60;
      }
      if (notifyMaxCount != 0) {
        Point dialogInfo=smartNotificationsDialogs.get(dialog_id);
        if (dialogInfo == null) {
          dialogInfo=new Point(1,(int)(System.currentTimeMillis() / 1000));
          smartNotificationsDialogs.put(dialog_id,dialogInfo);
        }
 else {
          int lastTime=dialogInfo.y;
          if (lastTime + notifyDelay < System.currentTimeMillis() / 1000) {
            dialogInfo.set(1,(int)(System.currentTimeMillis() / 1000));
          }
 else {
            int count=dialogInfo.x;
            if (count < notifyMaxCount) {
              dialogInfo.set(count + 1,(int)(System.currentTimeMillis() / 1000));
            }
 else {
              notifyDisabled=true;
            }
          }
        }
      }
    }
    String defaultPath=Settings.System.DEFAULT_NOTIFICATION_URI.getPath();
    boolean inAppSounds=preferences.getBoolean("EnableInAppSounds",true);
    boolean inAppVibrate=preferences.getBoolean("EnableInAppVibrate",true);
    boolean inAppPreview=preferences.getBoolean("EnableInAppPreview",true);
    boolean inAppPriority=preferences.getBoolean("EnableInAppPriority",false);
    boolean custom;
    int vibrateOverride;
    int priorityOverride;
    if (custom=preferences.getBoolean("custom_" + dialog_id,false)) {
      vibrateOverride=preferences.getInt("vibrate_" + dialog_id,0);
      priorityOverride=preferences.getInt("priority_" + dialog_id,3);
      choosenSoundPath=preferences.getString("sound_path_" + dialog_id,null);
    }
 else {
      vibrateOverride=0;
      priorityOverride=3;
      choosenSoundPath=null;
    }
    boolean vibrateOnlyIfSilent=false;
    if (chat_id != 0) {
      if (isChannel) {
        if (choosenSoundPath != null && choosenSoundPath.equals(defaultPath)) {
          choosenSoundPath=null;
        }
 else         if (choosenSoundPath == null) {
          choosenSoundPath=preferences.getString("ChannelSoundPath",defaultPath);
        }
        needVibrate=preferences.getInt("vibrate_channel",0);
        priority=preferences.getInt("priority_channel",1);
        ledColor=preferences.getInt("ChannelLed",0xff0000ff);
      }
 else {
        if (choosenSoundPath != null && choosenSoundPath.equals(defaultPath)) {
          choosenSoundPath=null;
        }
 else         if (choosenSoundPath == null) {
          choosenSoundPath=preferences.getString("GroupSoundPath",defaultPath);
        }
        needVibrate=preferences.getInt("vibrate_group",0);
        priority=preferences.getInt("priority_group",1);
        ledColor=preferences.getInt("GroupLed",0xff0000ff);
      }
    }
 else     if (user_id != 0) {
      if (choosenSoundPath != null && choosenSoundPath.equals(defaultPath)) {
        choosenSoundPath=null;
      }
 else       if (choosenSoundPath == null) {
        choosenSoundPath=preferences.getString("GlobalSoundPath",defaultPath);
      }
      needVibrate=preferences.getInt("vibrate_messages",0);
      priority=preferences.getInt("priority_messages",1);
      ledColor=preferences.getInt("MessagesLed",0xff0000ff);
    }
    if (custom) {
      if (preferences.contains("color_" + dialog_id)) {
        ledColor=preferences.getInt("color_" + dialog_id,0);
      }
    }
    if (priorityOverride != 3) {
      priority=priorityOverride;
    }
    if (needVibrate == 4) {
      vibrateOnlyIfSilent=true;
      needVibrate=0;
    }
    if (needVibrate == 2 && (vibrateOverride == 1 || vibrateOverride == 3) || needVibrate != 2 && vibrateOverride == 2 || vibrateOverride != 0 && vibrateOverride != 4) {
      needVibrate=vibrateOverride;
    }
    if (!ApplicationLoader.mainInterfacePaused) {
      if (!inAppSounds) {
        choosenSoundPath=null;
      }
      if (!inAppVibrate) {
        needVibrate=2;
      }
      if (!inAppPriority) {
        priority=0;
      }
 else       if (priority == 2) {
        priority=1;
      }
    }
    if (vibrateOnlyIfSilent && needVibrate != 2) {
      try {
        int mode=audioManager.getRingerMode();
        if (mode != AudioManager.RINGER_MODE_SILENT && mode != AudioManager.RINGER_MODE_VIBRATE) {
          needVibrate=2;
        }
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    Uri configSound=null;
    long[] configVibrationPattern=null;
    int configImportance=0;
    if (Build.VERSION.SDK_INT >= 26) {
      if (needVibrate == 2) {
        configVibrationPattern=new long[]{0,0};
      }
 else       if (needVibrate == 1) {
        configVibrationPattern=new long[]{0,100,0,100};
      }
 else       if (needVibrate == 0 || needVibrate == 4) {
        configVibrationPattern=new long[]{};
      }
 else       if (needVibrate == 3) {
        configVibrationPattern=new long[]{0,1000};
      }
      if (choosenSoundPath != null && !choosenSoundPath.equals("NoSound")) {
        if (choosenSoundPath.equals(defaultPath)) {
          configSound=Settings.System.DEFAULT_NOTIFICATION_URI;
        }
 else {
          configSound=Uri.parse(choosenSoundPath);
        }
      }
      if (priority == 0) {
        configImportance=NotificationManager.IMPORTANCE_DEFAULT;
      }
 else       if (priority == 1 || priority == 2) {
        configImportance=NotificationManager.IMPORTANCE_HIGH;
      }
 else       if (priority == 4) {
        configImportance=NotificationManager.IMPORTANCE_MIN;
      }
 else       if (priority == 5) {
        configImportance=NotificationManager.IMPORTANCE_LOW;
      }
    }
    if (notifyDisabled) {
      needVibrate=0;
      priority=0;
      ledColor=0;
      choosenSoundPath=null;
    }
    Intent intent=new Intent(ApplicationLoader.applicationContext,LaunchActivity.class);
    intent.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    if ((int)dialog_id != 0) {
      if (pushDialogs.size() == 1) {
        if (chat_id != 0) {
          intent.putExtra("chatId",chat_id);
        }
 else         if (user_id != 0) {
          intent.putExtra("userId",user_id);
        }
      }
      if (AndroidUtilities.needShowPasscode(false) || SharedConfig.isWaitingForPasscodeEnter) {
        photoPath=null;
      }
 else {
        if (pushDialogs.size() == 1 && Build.VERSION.SDK_INT < 28) {
          if (chat != null) {
            if (chat.photo != null && chat.photo.photo_small != null && chat.photo.photo_small.volume_id != 0 && chat.photo.photo_small.local_id != 0) {
              photoPath=chat.photo.photo_small;
            }
          }
 else           if (user != null) {
            if (user.photo != null && user.photo.photo_small != null && user.photo.photo_small.volume_id != 0 && user.photo.photo_small.local_id != 0) {
              photoPath=user.photo.photo_small;
            }
          }
        }
      }
    }
 else {
      if (pushDialogs.size() == 1 && dialog_id != globalSecretChatId) {
        intent.putExtra("encId",(int)(dialog_id >> 32));
      }
    }
    intent.putExtra("currentAccount",currentAccount);
    PendingIntent contentIntent=PendingIntent.getActivity(ApplicationLoader.applicationContext,0,intent,PendingIntent.FLAG_ONE_SHOT);
    String name;
    String chatName;
    boolean replace=true;
    if (((chat_id != 0 && chat == null) || user == null) && lastMessageObject.isFcmMessage()) {
      chatName=lastMessageObject.localName;
    }
 else     if (chat != null) {
      chatName=chat.title;
    }
 else {
      chatName=UserObject.getUserName(user);
    }
    if ((int)dialog_id == 0 || pushDialogs.size() > 1 || AndroidUtilities.needShowPasscode(false) || SharedConfig.isWaitingForPasscodeEnter) {
      name=LocaleController.getString("AppName",R.string.AppName);
      replace=false;
    }
 else {
      name=chatName;
    }
    String detailText;
    if (UserConfig.getActivatedAccountsCount() > 1) {
      if (pushDialogs.size() == 1) {
        detailText=UserObject.getFirstName(UserConfig.getInstance(currentAccount).getCurrentUser());
      }
 else {
        detailText=UserObject.getFirstName(UserConfig.getInstance(currentAccount).getCurrentUser()) + "?";
      }
    }
 else {
      detailText="";
    }
    if (pushDialogs.size() != 1 || Build.VERSION.SDK_INT < 23) {
      if (pushDialogs.size() == 1) {
        detailText+=LocaleController.formatPluralString("NewMessages",total_unread_count);
      }
 else {
        detailText+=LocaleController.formatString("NotificationMessagesPeopleDisplayOrder",R.string.NotificationMessagesPeopleDisplayOrder,LocaleController.formatPluralString("NewMessages",total_unread_count),LocaleController.formatPluralString("FromChats",pushDialogs.size()));
      }
    }
    NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(ApplicationLoader.applicationContext).setContentTitle(name).setSmallIcon(R.drawable.notification).setAutoCancel(true).setNumber(total_unread_count).setContentIntent(contentIntent).setGroup(notificationGroup).setGroupSummary(true).setShowWhen(true).setWhen(((long)lastMessageObject.messageOwner.date) * 1000).setColor(0xff11acfa);
    long[] vibrationPattern=null;
    int importance=0;
    Uri sound=null;
    mBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
    if (chat == null && user != null && user.phone != null && user.phone.length() > 0) {
      mBuilder.addPerson("tel:+" + user.phone);
    }
    int silent=2;
    String lastMessage=null;
    boolean hasNewMessages=false;
    if (pushMessages.size() == 1) {
      MessageObject messageObject=pushMessages.get(0);
      boolean[] text=new boolean[1];
      String message=lastMessage=getStringForMessage(messageObject,false,text,null);
      silent=messageObject.messageOwner.silent ? 1 : 0;
      if (message == null) {
        return;
      }
      if (replace) {
        if (chat != null) {
          message=message.replace(" @ " + name,"");
        }
 else {
          if (text[0]) {
            message=message.replace(name + ": ","");
          }
 else {
            message=message.replace(name + " ","");
          }
        }
      }
      mBuilder.setContentText(message);
      mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
    }
 else {
      mBuilder.setContentText(detailText);
      NotificationCompat.InboxStyle inboxStyle=new NotificationCompat.InboxStyle();
      inboxStyle.setBigContentTitle(name);
      int count=Math.min(10,pushMessages.size());
      boolean[] text=new boolean[1];
      for (int i=0; i < count; i++) {
        MessageObject messageObject=pushMessages.get(i);
        String message=getStringForMessage(messageObject,false,text,null);
        if (message == null || messageObject.messageOwner.date <= dismissDate) {
          continue;
        }
        if (silent == 2) {
          lastMessage=message;
          silent=messageObject.messageOwner.silent ? 1 : 0;
        }
        if (pushDialogs.size() == 1) {
          if (replace) {
            if (chat != null) {
              message=message.replace(" @ " + name,"");
            }
 else {
              if (text[0]) {
                message=message.replace(name + ": ","");
              }
 else {
                message=message.replace(name + " ","");
              }
            }
          }
        }
        inboxStyle.addLine(message);
      }
      inboxStyle.setSummaryText(detailText);
      mBuilder.setStyle(inboxStyle);
    }
    Intent dismissIntent=new Intent(ApplicationLoader.applicationContext,NotificationDismissReceiver.class);
    dismissIntent.putExtra("messageDate",lastMessageObject.messageOwner.date);
    dismissIntent.putExtra("currentAccount",currentAccount);
    mBuilder.setDeleteIntent(PendingIntent.getBroadcast(ApplicationLoader.applicationContext,1,dismissIntent,PendingIntent.FLAG_UPDATE_CURRENT));
    if (photoPath != null) {
      BitmapDrawable img=ImageLoader.getInstance().getImageFromMemory(photoPath,null,"50_50");
      if (img != null) {
        mBuilder.setLargeIcon(img.getBitmap());
      }
 else {
        try {
          File file=FileLoader.getPathToAttach(photoPath,true);
          if (file.exists()) {
            float scaleFactor=160.0f / AndroidUtilities.dp(50);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=scaleFactor < 1 ? 1 : (int)scaleFactor;
            Bitmap bitmap=BitmapFactory.decodeFile(file.getAbsolutePath(),options);
            if (bitmap != null) {
              mBuilder.setLargeIcon(bitmap);
            }
          }
        }
 catch (        Throwable ignore) {
        }
      }
    }
    if (!notifyAboutLast || silent == 1) {
      mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
      if (Build.VERSION.SDK_INT >= 26) {
        importance=NotificationManager.IMPORTANCE_LOW;
      }
    }
 else {
      if (priority == 0) {
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        if (Build.VERSION.SDK_INT >= 26) {
          importance=NotificationManager.IMPORTANCE_DEFAULT;
        }
      }
 else       if (priority == 1 || priority == 2) {
        mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        if (Build.VERSION.SDK_INT >= 26) {
          importance=NotificationManager.IMPORTANCE_HIGH;
        }
      }
 else       if (priority == 4) {
        mBuilder.setPriority(NotificationCompat.PRIORITY_MIN);
        if (Build.VERSION.SDK_INT >= 26) {
          importance=NotificationManager.IMPORTANCE_MIN;
        }
      }
 else       if (priority == 5) {
        mBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
        if (Build.VERSION.SDK_INT >= 26) {
          importance=NotificationManager.IMPORTANCE_LOW;
        }
      }
    }
    if (silent != 1 && !notifyDisabled) {
      if (ApplicationLoader.mainInterfacePaused || inAppPreview) {
        if (lastMessage.length() > 100) {
          lastMessage=lastMessage.substring(0,100).replace('\n',' ').trim() + "...";
        }
        mBuilder.setTicker(lastMessage);
      }
      if (!MediaController.getInstance().isRecordingAudio()) {
        if (choosenSoundPath != null && !choosenSoundPath.equals("NoSound")) {
          if (Build.VERSION.SDK_INT >= 26) {
            if (choosenSoundPath.equals(defaultPath)) {
              sound=Settings.System.DEFAULT_NOTIFICATION_URI;
            }
 else {
              sound=Uri.parse(choosenSoundPath);
            }
          }
 else {
            if (choosenSoundPath.equals(defaultPath)) {
              mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI,AudioManager.STREAM_NOTIFICATION);
            }
 else {
              if (Build.VERSION.SDK_INT >= 24 && choosenSoundPath.startsWith("file://") && !AndroidUtilities.isInternalUri(Uri.parse(choosenSoundPath))) {
                try {
                  Uri uri=FileProvider.getUriForFile(ApplicationLoader.applicationContext,BuildConfig.APPLICATION_ID + ".provider",new File(choosenSoundPath.replace("file://","")));
                  ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui",uri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
                  mBuilder.setSound(uri,AudioManager.STREAM_NOTIFICATION);
                }
 catch (                Exception e) {
                  mBuilder.setSound(Uri.parse(choosenSoundPath),AudioManager.STREAM_NOTIFICATION);
                }
              }
 else {
                mBuilder.setSound(Uri.parse(choosenSoundPath),AudioManager.STREAM_NOTIFICATION);
              }
            }
          }
        }
      }
      if (ledColor != 0) {
        mBuilder.setLights(ledColor,1000,1000);
      }
      if (needVibrate == 2 || MediaController.getInstance().isRecordingAudio()) {
        mBuilder.setVibrate(vibrationPattern=new long[]{0,0});
      }
 else       if (needVibrate == 1) {
        mBuilder.setVibrate(vibrationPattern=new long[]{0,100,0,100});
      }
 else       if (needVibrate == 0 || needVibrate == 4) {
        mBuilder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        vibrationPattern=new long[]{};
      }
 else       if (needVibrate == 3) {
        mBuilder.setVibrate(vibrationPattern=new long[]{0,1000});
      }
    }
 else {
      mBuilder.setVibrate(vibrationPattern=new long[]{0,0});
    }
    boolean hasCallback=false;
    if (!AndroidUtilities.needShowPasscode(false) && !SharedConfig.isWaitingForPasscodeEnter && lastMessageObject.getDialogId() == 777000) {
      if (lastMessageObject.messageOwner.reply_markup != null) {
        ArrayList<TLRPC.TL_keyboardButtonRow> rows=lastMessageObject.messageOwner.reply_markup.rows;
        for (int a=0, size=rows.size(); a < size; a++) {
          TLRPC.TL_keyboardButtonRow row=rows.get(a);
          for (int b=0, size2=row.buttons.size(); b < size2; b++) {
            TLRPC.KeyboardButton button=row.buttons.get(b);
            if (button instanceof TLRPC.TL_keyboardButtonCallback) {
              Intent callbackIntent=new Intent(ApplicationLoader.applicationContext,NotificationCallbackReceiver.class);
              callbackIntent.putExtra("currentAccount",currentAccount);
              callbackIntent.putExtra("did",dialog_id);
              if (button.data != null) {
                callbackIntent.putExtra("data",button.data);
              }
              callbackIntent.putExtra("mid",lastMessageObject.getId());
              mBuilder.addAction(0,button.text,PendingIntent.getBroadcast(ApplicationLoader.applicationContext,lastButtonId++,callbackIntent,PendingIntent.FLAG_UPDATE_CURRENT));
              hasCallback=true;
            }
          }
        }
      }
    }
    if (!hasCallback && Build.VERSION.SDK_INT < 24 && SharedConfig.passcodeHash.length() == 0 && hasMessagesToReply()) {
      Intent replyIntent=new Intent(ApplicationLoader.applicationContext,PopupReplyReceiver.class);
      replyIntent.putExtra("currentAccount",currentAccount);
      if (Build.VERSION.SDK_INT <= 19) {
        mBuilder.addAction(R.drawable.ic_ab_reply2,LocaleController.getString("Reply",R.string.Reply),PendingIntent.getBroadcast(ApplicationLoader.applicationContext,2,replyIntent,PendingIntent.FLAG_UPDATE_CURRENT));
      }
 else {
        mBuilder.addAction(R.drawable.ic_ab_reply,LocaleController.getString("Reply",R.string.Reply),PendingIntent.getBroadcast(ApplicationLoader.applicationContext,2,replyIntent,PendingIntent.FLAG_UPDATE_CURRENT));
      }
    }
    if (Build.VERSION.SDK_INT >= 26) {
      mBuilder.setChannelId(validateChannelId(dialog_id,chatName,vibrationPattern,ledColor,sound,importance,configVibrationPattern,configSound,configImportance));
    }
    showExtraNotifications(mBuilder,notifyAboutLast,detailText);
    scheduleNotificationRepeat();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
