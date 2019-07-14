@Override public List<ChooserTarget> onGetChooserTargets(ComponentName targetActivityName,IntentFilter matchedFilter){
  final int currentAccount=UserConfig.selectedAccount;
  final List<ChooserTarget> targets=new ArrayList<>();
  if (!UserConfig.getInstance(currentAccount).isClientActivated()) {
    return targets;
  }
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  if (!preferences.getBoolean("direct_share",true)) {
    return targets;
  }
  ImageLoader imageLoader=ImageLoader.getInstance();
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  final ComponentName componentName=new ComponentName(getPackageName(),LaunchActivity.class.getCanonicalName());
  MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> {
    ArrayList<Integer> dialogs=new ArrayList<>();
    ArrayList<TLRPC.Chat> chats=new ArrayList<>();
    ArrayList<TLRPC.User> users=new ArrayList<>();
    try {
      ArrayList<Integer> usersToLoad=new ArrayList<>();
      usersToLoad.add(UserConfig.getInstance(currentAccount).getClientUserId());
      ArrayList<Integer> chatsToLoad=new ArrayList<>();
      SQLiteCursor cursor=MessagesStorage.getInstance(currentAccount).getDatabase().queryFinalized(String.format(Locale.US,"SELECT did FROM dialogs ORDER BY date DESC LIMIT %d,%d",0,30));
      while (cursor.next()) {
        long id=cursor.longValue(0);
        int lower_id=(int)id;
        int high_id=(int)(id >> 32);
        if (lower_id != 0) {
          if (high_id == 1) {
            continue;
          }
 else {
            if (lower_id > 0) {
              if (!usersToLoad.contains(lower_id)) {
                usersToLoad.add(lower_id);
              }
            }
 else {
              if (!chatsToLoad.contains(-lower_id)) {
                chatsToLoad.add(-lower_id);
              }
            }
          }
        }
 else {
          continue;
        }
        dialogs.add(lower_id);
        if (dialogs.size() == 8) {
          break;
        }
      }
      cursor.dispose();
      if (!chatsToLoad.isEmpty()) {
        MessagesStorage.getInstance(currentAccount).getChatsInternal(TextUtils.join(",",chatsToLoad),chats);
      }
      if (!usersToLoad.isEmpty()) {
        MessagesStorage.getInstance(currentAccount).getUsersInternal(TextUtils.join(",",usersToLoad),users);
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    SharedConfig.directShareHash=Utilities.random.nextLong();
    ApplicationLoader.applicationContext.getSharedPreferences("mainconfig",Activity.MODE_PRIVATE).edit().putLong("directShareHash",SharedConfig.directShareHash).commit();
    for (int a=0; a < dialogs.size(); a++) {
      Bundle extras=new Bundle();
      Icon icon=null;
      String name=null;
      int id=dialogs.get(a);
      if (id > 0) {
        for (int b=0; b < users.size(); b++) {
          TLRPC.User user=users.get(b);
          if (user.id == id) {
            if (!user.bot) {
              extras.putLong("dialogId",(long)id);
              extras.putLong("hash",SharedConfig.directShareHash);
              if (user.photo != null && user.photo.photo_small != null) {
                icon=createRoundBitmap(FileLoader.getPathToAttach(user.photo.photo_small,true));
              }
              name=ContactsController.formatName(user.first_name,user.last_name);
            }
            break;
          }
        }
      }
 else {
        for (int b=0; b < chats.size(); b++) {
          TLRPC.Chat chat=chats.get(b);
          if (chat.id == -id) {
            if (!ChatObject.isNotInChat(chat) && (!ChatObject.isChannel(chat) || chat.megagroup)) {
              extras.putLong("dialogId",(long)id);
              extras.putLong("hash",SharedConfig.directShareHash);
              if (chat.photo != null && chat.photo.photo_small != null) {
                icon=createRoundBitmap(FileLoader.getPathToAttach(chat.photo.photo_small,true));
              }
              name=chat.title;
            }
            break;
          }
        }
      }
      if (name != null) {
        if (icon == null) {
          icon=Icon.createWithResource(ApplicationLoader.applicationContext,R.drawable.logo_avatar);
        }
        targets.add(new ChooserTarget(name,icon,1.0f,componentName,extras));
      }
    }
    countDownLatch.countDown();
  }
);
  try {
    countDownLatch.await();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return targets;
}
