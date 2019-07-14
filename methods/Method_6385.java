public void uninstallShortcut(long did){
  try {
    if (Build.VERSION.SDK_INT >= 26) {
      ShortcutManager shortcutManager=ApplicationLoader.applicationContext.getSystemService(ShortcutManager.class);
      ArrayList<String> arrayList=new ArrayList<>();
      arrayList.add("sdid_" + did);
      shortcutManager.removeDynamicShortcuts(arrayList);
    }
 else {
      int lower_id=(int)did;
      int high_id=(int)(did >> 32);
      TLRPC.User user=null;
      TLRPC.Chat chat=null;
      if (lower_id == 0) {
        TLRPC.EncryptedChat encryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat(high_id);
        if (encryptedChat == null) {
          return;
        }
        user=MessagesController.getInstance(currentAccount).getUser(encryptedChat.user_id);
      }
 else       if (lower_id > 0) {
        user=MessagesController.getInstance(currentAccount).getUser(lower_id);
      }
 else       if (lower_id < 0) {
        chat=MessagesController.getInstance(currentAccount).getChat(-lower_id);
      }
 else {
        return;
      }
      if (user == null && chat == null) {
        return;
      }
      String name;
      if (user != null) {
        name=ContactsController.formatName(user.first_name,user.last_name);
      }
 else {
        name=chat.title;
      }
      Intent addIntent=new Intent();
      addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,createIntrnalShortcutIntent(did));
      addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,name);
      addIntent.putExtra("duplicate",false);
      addIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
      ApplicationLoader.applicationContext.sendBroadcast(addIntent);
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
