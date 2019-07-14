private void processFoundUser(TLRPC.User user){
  contextUsernameReqid=0;
  locationProvider.stop();
  if (user != null && user.bot && user.bot_inline_placeholder != null) {
    foundContextBot=user;
    if (parentFragment != null) {
      TLRPC.Chat chat=parentFragment.getCurrentChat();
      if (chat != null) {
        inlineMediaEnabled=ChatObject.canSendStickers(chat);
        if (!inlineMediaEnabled) {
          notifyDataSetChanged();
          delegate.needChangePanelVisibility(true);
          return;
        }
      }
    }
    if (foundContextBot.bot_inline_geo) {
      SharedPreferences preferences=MessagesController.getNotificationsSettings(currentAccount);
      boolean allowGeo=preferences.getBoolean("inlinegeo_" + foundContextBot.id,false);
      if (!allowGeo && parentFragment != null && parentFragment.getParentActivity() != null) {
        final TLRPC.User foundContextBotFinal=foundContextBot;
        AlertDialog.Builder builder=new AlertDialog.Builder(parentFragment.getParentActivity());
        builder.setTitle(LocaleController.getString("ShareYouLocationTitle",R.string.ShareYouLocationTitle));
        builder.setMessage(LocaleController.getString("ShareYouLocationInline",R.string.ShareYouLocationInline));
        final boolean[] buttonClicked=new boolean[1];
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
          buttonClicked[0]=true;
          if (foundContextBotFinal != null) {
            SharedPreferences preferences1=MessagesController.getNotificationsSettings(currentAccount);
            preferences1.edit().putBoolean("inlinegeo_" + foundContextBotFinal.id,true).commit();
            checkLocationPermissionsOrStart();
          }
        }
);
        builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),(dialog,which) -> {
          buttonClicked[0]=true;
          onLocationUnavailable();
        }
);
        parentFragment.showDialog(builder.create(),dialog -> {
          if (!buttonClicked[0]) {
            onLocationUnavailable();
          }
        }
);
      }
 else {
        checkLocationPermissionsOrStart();
      }
    }
  }
 else {
    foundContextBot=null;
    inlineMediaEnabled=true;
  }
  if (foundContextBot == null) {
    noUserName=true;
  }
 else {
    if (delegate != null) {
      delegate.onContextSearch(true);
    }
    searchForContextBotResults(true,foundContextBot,searchingContextQuery,"");
  }
}
