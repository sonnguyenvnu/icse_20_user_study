public void buildShortcuts(){
  if (Build.VERSION.SDK_INT < 25) {
    return;
  }
  final ArrayList<TLRPC.TL_topPeer> hintsFinal=new ArrayList<>();
  for (int a=0; a < hints.size(); a++) {
    hintsFinal.add(hints.get(a));
    if (hintsFinal.size() == 3) {
      break;
    }
  }
  Utilities.globalQueue.postRunnable(() -> {
    try {
      ShortcutManager shortcutManager=ApplicationLoader.applicationContext.getSystemService(ShortcutManager.class);
      List<ShortcutInfo> currentShortcuts=shortcutManager.getDynamicShortcuts();
      ArrayList<String> shortcutsToUpdate=new ArrayList<>();
      ArrayList<String> newShortcutsIds=new ArrayList<>();
      ArrayList<String> shortcutsToDelete=new ArrayList<>();
      if (currentShortcuts != null && !currentShortcuts.isEmpty()) {
        newShortcutsIds.add("compose");
        for (int a=0; a < hintsFinal.size(); a++) {
          TLRPC.TL_topPeer hint=hintsFinal.get(a);
          long did;
          if (hint.peer.user_id != 0) {
            did=hint.peer.user_id;
          }
 else {
            did=-hint.peer.chat_id;
            if (did == 0) {
              did=-hint.peer.channel_id;
            }
          }
          newShortcutsIds.add("did" + did);
        }
        for (int a=0; a < currentShortcuts.size(); a++) {
          String id=currentShortcuts.get(a).getId();
          if (!newShortcutsIds.remove(id)) {
            shortcutsToDelete.add(id);
          }
          shortcutsToUpdate.add(id);
        }
        if (newShortcutsIds.isEmpty() && shortcutsToDelete.isEmpty()) {
          return;
        }
      }
      Intent intent=new Intent(ApplicationLoader.applicationContext,LaunchActivity.class);
      intent.setAction("new_dialog");
      ArrayList<ShortcutInfo> arrayList=new ArrayList<>();
      arrayList.add(new ShortcutInfo.Builder(ApplicationLoader.applicationContext,"compose").setShortLabel(LocaleController.getString("NewConversationShortcut",R.string.NewConversationShortcut)).setLongLabel(LocaleController.getString("NewConversationShortcut",R.string.NewConversationShortcut)).setIcon(Icon.createWithResource(ApplicationLoader.applicationContext,R.drawable.shortcut_compose)).setIntent(intent).build());
      if (shortcutsToUpdate.contains("compose")) {
        shortcutManager.updateShortcuts(arrayList);
      }
 else {
        shortcutManager.addDynamicShortcuts(arrayList);
      }
      arrayList.clear();
      if (!shortcutsToDelete.isEmpty()) {
        shortcutManager.removeDynamicShortcuts(shortcutsToDelete);
      }
      for (int a=0; a < hintsFinal.size(); a++) {
        Intent shortcutIntent=new Intent(ApplicationLoader.applicationContext,OpenChatReceiver.class);
        TLRPC.TL_topPeer hint=hintsFinal.get(a);
        TLRPC.User user=null;
        TLRPC.Chat chat=null;
        long did;
        if (hint.peer.user_id != 0) {
          shortcutIntent.putExtra("userId",hint.peer.user_id);
          user=MessagesController.getInstance(currentAccount).getUser(hint.peer.user_id);
          did=hint.peer.user_id;
        }
 else {
          int chat_id=hint.peer.chat_id;
          if (chat_id == 0) {
            chat_id=hint.peer.channel_id;
          }
          chat=MessagesController.getInstance(currentAccount).getChat(chat_id);
          shortcutIntent.putExtra("chatId",chat_id);
          did=-chat_id;
        }
        if ((user == null || UserObject.isDeleted(user)) && chat == null) {
          continue;
        }
        String name;
        TLRPC.FileLocation photo=null;
        if (user != null) {
          name=ContactsController.formatName(user.first_name,user.last_name);
          if (user.photo != null) {
            photo=user.photo.photo_small;
          }
        }
 else {
          name=chat.title;
          if (chat.photo != null) {
            photo=chat.photo.photo_small;
          }
        }
        shortcutIntent.putExtra("currentAccount",currentAccount);
        shortcutIntent.setAction("com.tmessages.openchat" + did);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bitmap bitmap=null;
        if (photo != null) {
          try {
            File path=FileLoader.getPathToAttach(photo,true);
            bitmap=BitmapFactory.decodeFile(path.toString());
            if (bitmap != null) {
              int size=AndroidUtilities.dp(48);
              Bitmap result=Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
              Canvas canvas=new Canvas(result);
              if (roundPaint == null) {
                roundPaint=new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
                bitmapRect=new RectF();
                erasePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
                erasePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                roundPath=new Path();
                roundPath.addCircle(size / 2,size / 2,size / 2 - AndroidUtilities.dp(2),Path.Direction.CW);
                roundPath.toggleInverseFillType();
              }
              bitmapRect.set(AndroidUtilities.dp(2),AndroidUtilities.dp(2),AndroidUtilities.dp(46),AndroidUtilities.dp(46));
              canvas.drawBitmap(bitmap,null,bitmapRect,roundPaint);
              canvas.drawPath(roundPath,erasePaint);
              try {
                canvas.setBitmap(null);
              }
 catch (              Exception ignore) {
              }
              bitmap=result;
            }
          }
 catch (          Throwable e) {
            FileLog.e(e);
          }
        }
        String id="did" + did;
        if (TextUtils.isEmpty(name)) {
          name=" ";
        }
        ShortcutInfo.Builder builder=new ShortcutInfo.Builder(ApplicationLoader.applicationContext,id).setShortLabel(name).setLongLabel(name).setIntent(shortcutIntent);
        if (bitmap != null) {
          builder.setIcon(Icon.createWithBitmap(bitmap));
        }
 else {
          builder.setIcon(Icon.createWithResource(ApplicationLoader.applicationContext,R.drawable.shortcut_user));
        }
        arrayList.add(builder.build());
        if (shortcutsToUpdate.contains(id)) {
          shortcutManager.updateShortcuts(arrayList);
        }
 else {
          shortcutManager.addDynamicShortcuts(arrayList);
        }
        arrayList.clear();
      }
    }
 catch (    Throwable ignore) {
    }
  }
);
}
