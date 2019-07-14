@Override @SuppressWarnings("unchecked") public void didReceivedNotification(int id,final int account,Object... args){
  if (id == NotificationCenter.appDidLogout) {
    switchToAvailableAccountOrLogout();
  }
 else   if (id == NotificationCenter.closeOtherAppActivities) {
    if (args[0] != this) {
      onFinish();
      finish();
    }
  }
 else   if (id == NotificationCenter.didUpdateConnectionState) {
    int state=ConnectionsManager.getInstance(account).getConnectionState();
    if (currentConnectionState != state) {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.d("switch to state " + state);
      }
      currentConnectionState=state;
      updateCurrentConnectionState(account);
    }
  }
 else   if (id == NotificationCenter.mainUserInfoChanged) {
    drawerLayoutAdapter.notifyDataSetChanged();
  }
 else   if (id == NotificationCenter.needShowAlert) {
    final Integer reason=(Integer)args[0];
    if (reason == 3 && proxyErrorDialog != null) {
      return;
    }
 else     if (reason == 4) {
      showTosActivity(account,(TLRPC.TL_help_termsOfService)args[1]);
      return;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    if (reason != 2 && reason != 3) {
      builder.setNegativeButton(LocaleController.getString("MoreInfo",R.string.MoreInfo),(dialogInterface,i) -> {
        if (!mainFragmentsStack.isEmpty()) {
          MessagesController.getInstance(account).openByUserName("spambot",mainFragmentsStack.get(mainFragmentsStack.size() - 1),1);
        }
      }
);
    }
    if (reason == 5) {
      builder.setMessage(LocaleController.getString("NobodyLikesSpam3",R.string.NobodyLikesSpam3));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
    }
 else     if (reason == 0) {
      builder.setMessage(LocaleController.getString("NobodyLikesSpam1",R.string.NobodyLikesSpam1));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
    }
 else     if (reason == 1) {
      builder.setMessage(LocaleController.getString("NobodyLikesSpam2",R.string.NobodyLikesSpam2));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
    }
 else     if (reason == 2) {
      builder.setMessage((String)args[1]);
      String type=(String)args[2];
      if (type.startsWith("AUTH_KEY_DROP_")) {
        builder.setPositiveButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        builder.setNegativeButton(LocaleController.getString("LogOut",R.string.LogOut),(dialog,which) -> MessagesController.getInstance(currentAccount).performLogout(2));
      }
 else {
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
      }
    }
 else     if (reason == 3) {
      builder.setMessage(LocaleController.getString("UseProxyTelegramError",R.string.UseProxyTelegramError));
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
      proxyErrorDialog=showAlertDialog(builder);
      return;
    }
    if (!mainFragmentsStack.isEmpty()) {
      mainFragmentsStack.get(mainFragmentsStack.size() - 1).showDialog(builder.create());
    }
  }
 else   if (id == NotificationCenter.wasUnableToFindCurrentLocation) {
    final HashMap<String,MessageObject> waitingForLocation=(HashMap<String,MessageObject>)args[0];
    AlertDialog.Builder builder=new AlertDialog.Builder(this);
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
    builder.setNegativeButton(LocaleController.getString("ShareYouLocationUnableManually",R.string.ShareYouLocationUnableManually),(dialogInterface,i) -> {
      if (mainFragmentsStack.isEmpty()) {
        return;
      }
      BaseFragment lastFragment=mainFragmentsStack.get(mainFragmentsStack.size() - 1);
      if (!AndroidUtilities.isGoogleMapsInstalled(lastFragment)) {
        return;
      }
      LocationActivity fragment=new LocationActivity(0);
      fragment.setDelegate((location,live) -> {
        for (        HashMap.Entry<String,MessageObject> entry : waitingForLocation.entrySet()) {
          MessageObject messageObject=entry.getValue();
          SendMessagesHelper.getInstance(account).sendMessage(location,messageObject.getDialogId(),messageObject,null,null);
        }
      }
);
      presentFragment(fragment);
    }
);
    builder.setMessage(LocaleController.getString("ShareYouLocationUnable",R.string.ShareYouLocationUnable));
    if (!mainFragmentsStack.isEmpty()) {
      mainFragmentsStack.get(mainFragmentsStack.size() - 1).showDialog(builder.create());
    }
  }
 else   if (id == NotificationCenter.didSetNewWallpapper) {
    if (sideMenu != null) {
      View child=sideMenu.getChildAt(0);
      if (child != null) {
        child.invalidate();
      }
    }
  }
 else   if (id == NotificationCenter.didSetPasscode) {
    if (SharedConfig.passcodeHash.length() > 0 && !SharedConfig.allowScreenCapture) {
      try {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
 else     if (!MediaController.getInstance().hasFlagSecureFragment()) {
      try {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
  }
 else   if (id == NotificationCenter.reloadInterface) {
    boolean last=mainFragmentsStack.size() > 1 && mainFragmentsStack.get(mainFragmentsStack.size() - 1) instanceof SettingsActivity;
    rebuildAllFragments(last);
  }
 else   if (id == NotificationCenter.suggestedLangpack) {
    showLanguageAlert(false);
  }
 else   if (id == NotificationCenter.openArticle) {
    if (mainFragmentsStack.isEmpty()) {
      return;
    }
    ArticleViewer.getInstance().setParentActivity(this,mainFragmentsStack.get(mainFragmentsStack.size() - 1));
    ArticleViewer.getInstance().open((TLRPC.TL_webPage)args[0],(String)args[1]);
  }
 else   if (id == NotificationCenter.hasNewContactsToImport) {
    if (actionBarLayout == null || actionBarLayout.fragmentsStack.isEmpty()) {
      return;
    }
    final int type=(Integer)args[0];
    final HashMap<String,ContactsController.Contact> contactHashMap=(HashMap<String,ContactsController.Contact>)args[1];
    final boolean first=(Boolean)args[2];
    final boolean schedule=(Boolean)args[3];
    BaseFragment fragment=actionBarLayout.fragmentsStack.get(actionBarLayout.fragmentsStack.size() - 1);
    AlertDialog.Builder builder=new AlertDialog.Builder(LaunchActivity.this);
    builder.setTitle(LocaleController.getString("UpdateContactsTitle",R.string.UpdateContactsTitle));
    builder.setMessage(LocaleController.getString("UpdateContactsMessage",R.string.UpdateContactsMessage));
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> ContactsController.getInstance(account).syncPhoneBookByAlert(contactHashMap,first,schedule,false));
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),(dialog,which) -> ContactsController.getInstance(account).syncPhoneBookByAlert(contactHashMap,first,schedule,true));
    builder.setOnBackButtonListener((dialogInterface,i) -> ContactsController.getInstance(account).syncPhoneBookByAlert(contactHashMap,first,schedule,true));
    AlertDialog dialog=builder.create();
    fragment.showDialog(dialog);
    dialog.setCanceledOnTouchOutside(false);
  }
 else   if (id == NotificationCenter.didSetNewTheme) {
    Boolean nightTheme=(Boolean)args[0];
    if (!nightTheme) {
      if (sideMenu != null) {
        sideMenu.setBackgroundColor(Theme.getColor(Theme.key_chats_menuBackground));
        sideMenu.setGlowColor(Theme.getColor(Theme.key_chats_menuBackground));
        sideMenu.setListSelectorColor(Theme.getColor(Theme.key_listSelector));
        sideMenu.getAdapter().notifyDataSetChanged();
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        try {
          setTaskDescription(new ActivityManager.TaskDescription(null,null,Theme.getColor(Theme.key_actionBarDefault) | 0xff000000));
        }
 catch (        Exception ignore) {
        }
      }
    }
    drawerLayoutContainer.setBehindKeyboardColor(Theme.getColor(Theme.key_windowBackgroundWhite));
  }
 else   if (id == NotificationCenter.needSetDayNightTheme) {
    Theme.ThemeInfo theme=(Theme.ThemeInfo)args[0];
    boolean nigthTheme=(Boolean)args[1];
    actionBarLayout.animateThemedValues(theme,nigthTheme);
    if (AndroidUtilities.isTablet()) {
      layersActionBarLayout.animateThemedValues(theme,nigthTheme);
      rightActionBarLayout.animateThemedValues(theme,nigthTheme);
    }
  }
 else   if (id == NotificationCenter.notificationsCountUpdated) {
    if (sideMenu != null) {
      Integer accountNum=(Integer)args[0];
      int count=sideMenu.getChildCount();
      for (int a=0; a < count; a++) {
        View child=sideMenu.getChildAt(a);
        if (child instanceof DrawerUserCell) {
          if (((DrawerUserCell)child).getAccountNumber() == accountNum) {
            child.invalidate();
            break;
          }
        }
      }
    }
  }
}
