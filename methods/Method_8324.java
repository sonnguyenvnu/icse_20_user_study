public void showOpenGameAlert(final TLRPC.TL_game game,final MessageObject messageObject,final String urlStr,boolean ask,final int uid){
  TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(uid);
  if (ask) {
    AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    String name;
    if (user != null) {
      name=ContactsController.formatName(user.first_name,user.last_name);
    }
 else {
      name="";
    }
    builder.setMessage(LocaleController.formatString("BotPermissionGameAlert",R.string.BotPermissionGameAlert,name));
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
      showOpenGameAlert(game,messageObject,urlStr,false,uid);
      MessagesController.getNotificationsSettings(currentAccount).edit().putBoolean("askgame_" + uid,false).commit();
    }
);
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    showDialog(builder.create());
  }
 else {
    if (Build.VERSION.SDK_INT >= 21 && !AndroidUtilities.isTablet() && WebviewActivity.supportWebview()) {
      if (parentLayout.fragmentsStack.get(parentLayout.fragmentsStack.size() - 1) == this) {
        presentFragment(new WebviewActivity(urlStr,user != null && !TextUtils.isEmpty(user.username) ? user.username : "",game.title,game.short_name,messageObject));
      }
    }
 else {
      WebviewActivity.openGameInBrowser(urlStr,messageObject,getParentActivity(),game.short_name,user != null && user.username != null ? user.username : "");
    }
  }
}
