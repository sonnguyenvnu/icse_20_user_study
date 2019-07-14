@SuppressLint("InlinedApi") private void showExtraNotifications(NotificationCompat.Builder notificationBuilder,boolean notifyAboutLast,String summary){
  Notification mainNotification=notificationBuilder.build();
  if (Build.VERSION.SDK_INT < 18) {
    notificationManager.notify(notificationId,mainNotification);
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("show summary notification by SDK check");
    }
    return;
  }
  ArrayList<Long> sortedDialogs=new ArrayList<>();
  LongSparseArray<ArrayList<MessageObject>> messagesByDialogs=new LongSparseArray<>();
  for (int a=0; a < pushMessages.size(); a++) {
    MessageObject messageObject=pushMessages.get(a);
    long dialog_id=messageObject.getDialogId();
    ArrayList<MessageObject> arrayList=messagesByDialogs.get(dialog_id);
    if (arrayList == null) {
      arrayList=new ArrayList<>();
      messagesByDialogs.put(dialog_id,arrayList);
      sortedDialogs.add(0,dialog_id);
    }
    arrayList.add(messageObject);
  }
  LongSparseArray<Integer> oldIdsWear=wearNotificationsIds.clone();
  wearNotificationsIds.clear();
class NotificationHolder {
    NotificationHolder(    int i,    Notification n){
      id=i;
      notification=n;
    }
    void call(){
      if (BuildVars.LOGS_ENABLED) {
        FileLog.w("show dialog notification with id " + id);
      }
      notificationManager.notify(id,notification);
    }
  }
  ArrayList<NotificationHolder> holders=new ArrayList<>();
  JSONArray serializedNotifications=null;
  if (WearDataLayerListenerService.isWatchConnected()) {
    serializedNotifications=new JSONArray();
  }
  boolean useSummaryNotification=Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1 || Build.VERSION.SDK_INT > Build.VERSION_CODES.O_MR1 && sortedDialogs.size() > 1;
  if (useSummaryNotification && Build.VERSION.SDK_INT >= 26) {
    checkOtherNotificationsChannel();
  }
  for (int b=0, size=sortedDialogs.size(); b < size; b++) {
    long dialog_id=sortedDialogs.get(b);
    ArrayList<MessageObject> messageObjects=messagesByDialogs.get(dialog_id);
    int max_id=messageObjects.get(0).getId();
    int lowerId=(int)dialog_id;
    int highId=(int)(dialog_id >> 32);
    Integer internalId=oldIdsWear.get(dialog_id);
    if (internalId == null) {
      if (lowerId != 0) {
        internalId=lowerId;
      }
 else {
        internalId=highId;
      }
    }
 else {
      oldIdsWear.remove(dialog_id);
    }
    JSONObject serializedChat=null;
    if (serializedNotifications != null) {
      serializedChat=new JSONObject();
    }
    MessageObject lastMessageObject=messageObjects.get(0);
    int max_date=lastMessageObject.messageOwner.date;
    TLRPC.Chat chat=null;
    TLRPC.User user=null;
    boolean isChannel=false;
    boolean isSupergroup=false;
    String name;
    TLRPC.FileLocation photoPath=null;
    Bitmap avatarBitmap=null;
    File avatalFile=null;
    boolean canReply;
    LongSparseArray<Person> personCache=new LongSparseArray<>();
    if (lowerId != 0) {
      canReply=lowerId != 777000;
      if (lowerId > 0) {
        user=MessagesController.getInstance(currentAccount).getUser(lowerId);
        if (user == null) {
          if (lastMessageObject.isFcmMessage()) {
            name=lastMessageObject.localName;
          }
 else {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.w("not found user to show dialog notification " + lowerId);
            }
            continue;
          }
        }
 else {
          name=UserObject.getUserName(user);
          if (user.photo != null && user.photo.photo_small != null && user.photo.photo_small.volume_id != 0 && user.photo.photo_small.local_id != 0) {
            photoPath=user.photo.photo_small;
          }
        }
      }
 else {
        chat=MessagesController.getInstance(currentAccount).getChat(-lowerId);
        if (chat == null) {
          if (lastMessageObject.isFcmMessage()) {
            isSupergroup=lastMessageObject.isMegagroup();
            name=lastMessageObject.localName;
            isChannel=lastMessageObject.localChannel;
          }
 else {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.w("not found chat to show dialog notification " + lowerId);
            }
            continue;
          }
        }
 else {
          isSupergroup=chat.megagroup;
          isChannel=ChatObject.isChannel(chat) && !chat.megagroup;
          name=chat.title;
          if (chat.photo != null && chat.photo.photo_small != null && chat.photo.photo_small.volume_id != 0 && chat.photo.photo_small.local_id != 0) {
            photoPath=chat.photo.photo_small;
          }
        }
      }
    }
 else {
      canReply=false;
      if (dialog_id != globalSecretChatId) {
        TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(highId);
        if (encryptedChat == null) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.w("not found secret chat to show dialog notification " + highId);
          }
          continue;
        }
        user=MessagesController.getInstance(currentAccount).getUser(encryptedChat.user_id);
        if (user == null) {
          if (BuildVars.LOGS_ENABLED) {
            FileLog.w("not found secret chat user to show dialog notification " + encryptedChat.user_id);
          }
          continue;
        }
      }
      name=LocaleController.getString("SecretChatName",R.string.SecretChatName);
      photoPath=null;
      serializedChat=null;
    }
    if (AndroidUtilities.needShowPasscode(false) || SharedConfig.isWaitingForPasscodeEnter) {
      name=LocaleController.getString("AppName",R.string.AppName);
      photoPath=null;
      canReply=false;
    }
    if (photoPath != null) {
      avatalFile=FileLoader.getPathToAttach(photoPath,true);
      BitmapDrawable img=ImageLoader.getInstance().getImageFromMemory(photoPath,null,"50_50");
      if (img != null) {
        avatarBitmap=img.getBitmap();
      }
 else       if (Build.VERSION.SDK_INT < 28) {
        try {
          if (avatalFile.exists()) {
            float scaleFactor=160.0f / AndroidUtilities.dp(50);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inSampleSize=scaleFactor < 1 ? 1 : (int)scaleFactor;
            avatarBitmap=BitmapFactory.decodeFile(avatalFile.getAbsolutePath(),options);
          }
        }
 catch (        Throwable ignore) {
        }
      }
    }
    NotificationCompat.Action wearReplyAction=null;
    if ((!isChannel || isSupergroup) && canReply && !SharedConfig.isWaitingForPasscodeEnter) {
      Intent replyIntent=new Intent(ApplicationLoader.applicationContext,WearReplyReceiver.class);
      replyIntent.putExtra("dialog_id",dialog_id);
      replyIntent.putExtra("max_id",max_id);
      replyIntent.putExtra("currentAccount",currentAccount);
      PendingIntent replyPendingIntent=PendingIntent.getBroadcast(ApplicationLoader.applicationContext,internalId,replyIntent,PendingIntent.FLAG_UPDATE_CURRENT);
      RemoteInput remoteInputWear=new RemoteInput.Builder(EXTRA_VOICE_REPLY).setLabel(LocaleController.getString("Reply",R.string.Reply)).build();
      String replyToString;
      if (lowerId < 0) {
        replyToString=LocaleController.formatString("ReplyToGroup",R.string.ReplyToGroup,name);
      }
 else {
        replyToString=LocaleController.formatString("ReplyToUser",R.string.ReplyToUser,name);
      }
      wearReplyAction=new NotificationCompat.Action.Builder(R.drawable.ic_reply_icon,replyToString,replyPendingIntent).setAllowGeneratedReplies(true).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_REPLY).addRemoteInput(remoteInputWear).setShowsUserInterface(false).build();
    }
    Integer count=pushDialogs.get(dialog_id);
    if (count == null) {
      count=0;
    }
    int n=Math.max(count,messageObjects.size());
    String conversationName;
    if (n <= 1 || Build.VERSION.SDK_INT >= 28) {
      conversationName=name;
    }
 else {
      conversationName=String.format("%1$s (%2$d)",name,n);
    }
    NotificationCompat.MessagingStyle messagingStyle=new NotificationCompat.MessagingStyle("");
    if (Build.VERSION.SDK_INT < 28 || lowerId < 0 && !isChannel) {
      messagingStyle.setConversationTitle(conversationName);
    }
    messagingStyle.setGroupConversation(Build.VERSION.SDK_INT < 28 || !isChannel && lowerId < 0);
    StringBuilder text=new StringBuilder();
    String[] senderName=new String[1];
    boolean[] preview=new boolean[1];
    ArrayList<TLRPC.TL_keyboardButtonRow> rows=null;
    int rowsMid=0;
    JSONArray serializedMsgs=null;
    if (serializedChat != null) {
      serializedMsgs=new JSONArray();
    }
    for (int a=messageObjects.size() - 1; a >= 0; a--) {
      MessageObject messageObject=messageObjects.get(a);
      String message=getShortStringForMessage(messageObject,senderName,preview);
      if (message == null) {
        if (BuildVars.LOGS_ENABLED) {
          FileLog.w("message text is null for " + messageObject.getId() + " did = " + messageObject.getDialogId());
        }
        continue;
      }
      if (text.length() > 0) {
        text.append("\n\n");
      }
      if (senderName[0] != null) {
        text.append(String.format("%1$s: %2$s",senderName[0],message));
      }
 else {
        text.append(message);
      }
      long uid;
      if (lowerId > 0) {
        uid=lowerId;
      }
 else       if (isChannel) {
        uid=-lowerId;
      }
 else       if (lowerId < 0) {
        uid=messageObject.getFromId();
      }
 else {
        uid=dialog_id;
      }
      Person person=personCache.get(uid);
      if (person == null) {
        Person.Builder personBuilder=new Person.Builder().setName(senderName[0] == null ? "" : senderName[0]);
        if (preview[0] && lowerId != 0 && Build.VERSION.SDK_INT >= 28) {
          File avatar=null;
          if (lowerId > 0 || isChannel) {
            avatar=avatalFile;
          }
 else           if (lowerId < 0) {
            int fromId=messageObject.getFromId();
            TLRPC.User sender=MessagesController.getInstance(currentAccount).getUser(fromId);
            if (sender == null) {
              sender=MessagesStorage.getInstance(currentAccount).getUserSync(fromId);
              if (sender != null) {
                MessagesController.getInstance(currentAccount).putUser(sender,true);
              }
            }
            if (sender != null && sender.photo != null && sender.photo.photo_small != null && sender.photo.photo_small.volume_id != 0 && sender.photo.photo_small.local_id != 0) {
              avatar=FileLoader.getPathToAttach(sender.photo.photo_small,true);
            }
          }
          loadRoundAvatar(avatar,personBuilder);
        }
        person=personBuilder.build();
        personCache.put(uid,person);
      }
      if (lowerId != 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && !((ActivityManager)ApplicationLoader.applicationContext.getSystemService(Context.ACTIVITY_SERVICE)).isLowRamDevice()) {
          if (messageObject.type == 1 || messageObject.isSticker()) {
            File attach=FileLoader.getPathToMessage(messageObject.messageOwner);
            NotificationCompat.MessagingStyle.Message msg=new NotificationCompat.MessagingStyle.Message(message,((long)messageObject.messageOwner.date) * 1000L,person);
            String mimeType=messageObject.isSticker() ? "image/webp" : "image/jpeg";
            final Uri uri;
            if (attach.exists()) {
              uri=FileProvider.getUriForFile(ApplicationLoader.applicationContext,BuildConfig.APPLICATION_ID + ".provider",attach);
            }
 else             if (FileLoader.getInstance(currentAccount).isLoadingFile(attach.getName())) {
              Uri.Builder _uri=new Uri.Builder().scheme("content").authority(NotificationImageProvider.AUTHORITY).appendPath("msg_media_raw").appendPath(currentAccount + "").appendPath(attach.getName()).appendQueryParameter("final_path",attach.getAbsolutePath());
              uri=_uri.build();
            }
 else {
              uri=null;
            }
            if (uri != null) {
              msg.setData(mimeType,uri);
              messagingStyle.addMessage(msg);
              ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui",uri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
              AndroidUtilities.runOnUIThread(() -> ApplicationLoader.applicationContext.revokeUriPermission(uri,Intent.FLAG_GRANT_READ_URI_PERMISSION),20000);
              if (!TextUtils.isEmpty(messageObject.caption)) {
                messagingStyle.addMessage(messageObject.caption,((long)messageObject.messageOwner.date) * 1000,person);
              }
            }
 else {
              messagingStyle.addMessage(message,((long)messageObject.messageOwner.date) * 1000,person);
            }
          }
 else {
            messagingStyle.addMessage(message,((long)messageObject.messageOwner.date) * 1000,person);
          }
        }
 else {
          messagingStyle.addMessage(message,((long)messageObject.messageOwner.date) * 1000,person);
        }
        if (messageObject.isVoice()) {
          List<NotificationCompat.MessagingStyle.Message> messages=messagingStyle.getMessages();
          if (!messages.isEmpty()) {
            File f=FileLoader.getPathToMessage(messageObject.messageOwner);
            Uri uri;
            if (Build.VERSION.SDK_INT >= 24) {
              try {
                uri=FileProvider.getUriForFile(ApplicationLoader.applicationContext,BuildConfig.APPLICATION_ID + ".provider",f);
              }
 catch (              Exception ignore) {
                uri=null;
              }
            }
 else {
              uri=Uri.fromFile(f);
            }
            if (uri != null) {
              NotificationCompat.MessagingStyle.Message addedMessage=messages.get(messages.size() - 1);
              addedMessage.setData("audio/ogg",uri);
            }
          }
        }
      }
 else {
        messagingStyle.addMessage(message,((long)messageObject.messageOwner.date) * 1000,person);
      }
      if (serializedMsgs != null) {
        try {
          JSONObject jmsg=new JSONObject();
          jmsg.put("text",message);
          jmsg.put("date",messageObject.messageOwner.date);
          if (messageObject.isFromUser() && lowerId < 0) {
            TLRPC.User sender=MessagesController.getInstance(currentAccount).getUser(messageObject.getFromId());
            if (sender != null) {
              jmsg.put("fname",sender.first_name);
              jmsg.put("lname",sender.last_name);
            }
          }
          serializedMsgs.put(jmsg);
        }
 catch (        JSONException ignore) {
        }
      }
      if (dialog_id == 777000 && messageObject.messageOwner.reply_markup != null) {
        rows=messageObject.messageOwner.reply_markup.rows;
        rowsMid=messageObject.getId();
      }
    }
    Intent intent=new Intent(ApplicationLoader.applicationContext,LaunchActivity.class);
    intent.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
    intent.addCategory(Intent.CATEGORY_LAUNCHER);
    if (lowerId != 0) {
      if (lowerId > 0) {
        intent.putExtra("userId",lowerId);
      }
 else {
        intent.putExtra("chatId",-lowerId);
      }
    }
 else {
      intent.putExtra("encId",highId);
    }
    intent.putExtra("currentAccount",currentAccount);
    PendingIntent contentIntent=PendingIntent.getActivity(ApplicationLoader.applicationContext,0,intent,PendingIntent.FLAG_ONE_SHOT);
    NotificationCompat.WearableExtender wearableExtender=new NotificationCompat.WearableExtender();
    if (wearReplyAction != null) {
      wearableExtender.addAction(wearReplyAction);
    }
    Intent msgHeardIntent=new Intent(ApplicationLoader.applicationContext,AutoMessageHeardReceiver.class);
    msgHeardIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
    msgHeardIntent.setAction("org.telegram.messenger.ACTION_MESSAGE_HEARD");
    msgHeardIntent.putExtra("dialog_id",dialog_id);
    msgHeardIntent.putExtra("max_id",max_id);
    msgHeardIntent.putExtra("currentAccount",currentAccount);
    PendingIntent readPendingIntent=PendingIntent.getBroadcast(ApplicationLoader.applicationContext,internalId,msgHeardIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    NotificationCompat.Action readAction=new NotificationCompat.Action.Builder(R.drawable.menu_read,LocaleController.getString("MarkAsRead",R.string.MarkAsRead),readPendingIntent).setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_MARK_AS_READ).setShowsUserInterface(false).build();
    String dismissalID;
    if (lowerId != 0) {
      if (lowerId > 0) {
        dismissalID="tguser" + lowerId + "_" + max_id;
      }
 else {
        dismissalID="tgchat" + (-lowerId) + "_" + max_id;
      }
    }
 else     if (dialog_id != globalSecretChatId) {
      dismissalID="tgenc" + highId + "_" + max_id;
    }
 else {
      dismissalID=null;
    }
    if (dismissalID != null) {
      wearableExtender.setDismissalId(dismissalID);
      NotificationCompat.WearableExtender summaryExtender=new NotificationCompat.WearableExtender();
      summaryExtender.setDismissalId("summary_" + dismissalID);
      notificationBuilder.extend(summaryExtender);
    }
    wearableExtender.setBridgeTag("tgaccount" + UserConfig.getInstance(currentAccount).getClientUserId());
    long date=((long)messageObjects.get(0).messageOwner.date) * 1000;
    NotificationCompat.Builder builder=new NotificationCompat.Builder(ApplicationLoader.applicationContext).setContentTitle(name).setSmallIcon(R.drawable.notification).setContentText(text.toString()).setAutoCancel(true).setNumber(messageObjects.size()).setColor(0xff11acfa).setGroupSummary(false).setWhen(date).setShowWhen(true).setShortcutId("sdid_" + dialog_id).setStyle(messagingStyle).setContentIntent(contentIntent).extend(wearableExtender).setSortKey("" + (Long.MAX_VALUE - date)).setCategory(NotificationCompat.CATEGORY_MESSAGE);
    if (useSummaryNotification) {
      builder.setGroup(notificationGroup);
      builder.setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_SUMMARY);
    }
    if (wearReplyAction != null) {
      builder.addAction(wearReplyAction);
    }
    builder.addAction(readAction);
    if (pushDialogs.size() == 1 && !TextUtils.isEmpty(summary)) {
      builder.setSubText(summary);
    }
    if (lowerId == 0) {
      builder.setLocalOnly(true);
    }
    if (avatarBitmap != null && Build.VERSION.SDK_INT < 28) {
      builder.setLargeIcon(avatarBitmap);
    }
    if (!AndroidUtilities.needShowPasscode(false) && !SharedConfig.isWaitingForPasscodeEnter && rows != null) {
      for (int r=0, rc=rows.size(); r < rc; r++) {
        TLRPC.TL_keyboardButtonRow row=rows.get(r);
        for (int c=0, cc=row.buttons.size(); c < cc; c++) {
          TLRPC.KeyboardButton button=row.buttons.get(c);
          if (button instanceof TLRPC.TL_keyboardButtonCallback) {
            Intent callbackIntent=new Intent(ApplicationLoader.applicationContext,NotificationCallbackReceiver.class);
            callbackIntent.putExtra("currentAccount",currentAccount);
            callbackIntent.putExtra("did",dialog_id);
            if (button.data != null) {
              callbackIntent.putExtra("data",button.data);
            }
            callbackIntent.putExtra("mid",rowsMid);
            builder.addAction(0,button.text,PendingIntent.getBroadcast(ApplicationLoader.applicationContext,lastButtonId++,callbackIntent,PendingIntent.FLAG_UPDATE_CURRENT));
          }
        }
      }
    }
    if (chat == null && user != null && user.phone != null && user.phone.length() > 0) {
      builder.addPerson("tel:+" + user.phone);
    }
    if (Build.VERSION.SDK_INT >= 26) {
      if (useSummaryNotification) {
        builder.setChannelId(OTHER_NOTIFICATIONS_CHANNEL);
      }
 else {
        builder.setChannelId(mainNotification.getChannelId());
      }
    }
    holders.add(new NotificationHolder(internalId,builder.build()));
    wearNotificationsIds.put(dialog_id,internalId);
    if (lowerId != 0) {
      try {
        if (serializedChat != null) {
          serializedChat.put("reply",canReply);
          serializedChat.put("name",name);
          serializedChat.put("max_id",max_id);
          serializedChat.put("max_date",max_date);
          serializedChat.put("id",Math.abs(lowerId));
          if (photoPath != null) {
            serializedChat.put("photo",photoPath.dc_id + "_" + photoPath.volume_id + "_" + photoPath.secret);
          }
          if (serializedMsgs != null) {
            serializedChat.put("msgs",serializedMsgs);
          }
          if (lowerId > 0) {
            serializedChat.put("type","user");
          }
 else           if (lowerId < 0) {
            if (isChannel || isSupergroup) {
              serializedChat.put("type","channel");
            }
 else {
              serializedChat.put("type","group");
            }
          }
          serializedNotifications.put(serializedChat);
        }
      }
 catch (      JSONException ignore) {
      }
    }
  }
  if (useSummaryNotification) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("show summary with id " + notificationId);
    }
    notificationManager.notify(notificationId,mainNotification);
  }
 else {
    notificationManager.cancel(notificationId);
  }
  for (int a=0, size=holders.size(); a < size; a++) {
    holders.get(a).call();
  }
  for (int a=0; a < oldIdsWear.size(); a++) {
    Integer id=oldIdsWear.valueAt(a);
    if (BuildVars.LOGS_ENABLED) {
      FileLog.w("cancel notification id " + id);
    }
    notificationManager.cancel(id);
  }
  if (serializedNotifications != null) {
    try {
      JSONObject s=new JSONObject();
      s.put("id",UserConfig.getInstance(currentAccount).getClientUserId());
      s.put("n",serializedNotifications);
      WearDataLayerListenerService.sendMessageToWatch("/notify",s.toString().getBytes(),"remote_notifications");
    }
 catch (    Exception ignore) {
    }
  }
}
