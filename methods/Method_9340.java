private void updateTabs(){
  if (scrollSlidingTextTabStrip == null) {
    return;
  }
  boolean changed=false;
  if ((hasMedia[0] != 0 || hasMedia[1] == 0 && hasMedia[2] == 0 && hasMedia[3] == 0 && hasMedia[4] == 0) && !scrollSlidingTextTabStrip.hasTab(0)) {
    changed=true;
  }
  if (hasMedia[1] != 0) {
    if (!scrollSlidingTextTabStrip.hasTab(1)) {
      changed=true;
    }
  }
  if ((int)dialog_id != 0) {
    if (hasMedia[3] != 0 && !scrollSlidingTextTabStrip.hasTab(3)) {
      changed=true;
    }
    if (hasMedia[4] != 0 && !scrollSlidingTextTabStrip.hasTab(4)) {
      changed=true;
    }
  }
 else {
    TLRPC.EncryptedChat currentEncryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat((int)(dialog_id >> 32));
    if (currentEncryptedChat != null && AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) >= 46) {
      if (hasMedia[4] != 0 && !scrollSlidingTextTabStrip.hasTab(4)) {
        changed=true;
      }
    }
  }
  if (hasMedia[2] != 0 && !scrollSlidingTextTabStrip.hasTab(2)) {
    changed=true;
  }
  if (changed) {
    scrollSlidingTextTabStrip.removeTabs();
    if (hasMedia[0] != 0 || hasMedia[1] == 0 && hasMedia[2] == 0 && hasMedia[3] == 0 && hasMedia[4] == 0) {
      if (!scrollSlidingTextTabStrip.hasTab(0)) {
        scrollSlidingTextTabStrip.addTextTab(0,LocaleController.getString("SharedMediaTab",R.string.SharedMediaTab));
      }
    }
    if (hasMedia[1] != 0) {
      if (!scrollSlidingTextTabStrip.hasTab(1)) {
        scrollSlidingTextTabStrip.addTextTab(1,LocaleController.getString("SharedFilesTab",R.string.SharedFilesTab));
      }
    }
    if ((int)dialog_id != 0) {
      if (hasMedia[3] != 0) {
        if (!scrollSlidingTextTabStrip.hasTab(3)) {
          scrollSlidingTextTabStrip.addTextTab(3,LocaleController.getString("SharedLinksTab",R.string.SharedLinksTab));
        }
      }
      if (hasMedia[4] != 0) {
        if (!scrollSlidingTextTabStrip.hasTab(4)) {
          scrollSlidingTextTabStrip.addTextTab(4,LocaleController.getString("SharedMusicTab",R.string.SharedMusicTab));
        }
      }
    }
 else {
      TLRPC.EncryptedChat currentEncryptedChat=MessagesController.getInstance(currentAccount).getEncryptedChat((int)(dialog_id >> 32));
      if (currentEncryptedChat != null && AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) >= 46) {
        if (hasMedia[4] != 0) {
          if (!scrollSlidingTextTabStrip.hasTab(4)) {
            scrollSlidingTextTabStrip.addTextTab(4,LocaleController.getString("SharedMusicTab",R.string.SharedMusicTab));
          }
        }
      }
    }
    if (hasMedia[2] != 0) {
      if (!scrollSlidingTextTabStrip.hasTab(2)) {
        scrollSlidingTextTabStrip.addTextTab(2,LocaleController.getString("SharedVoiceTab",R.string.SharedVoiceTab));
      }
    }
  }
  if (scrollSlidingTextTabStrip.getTabsCount() <= 1) {
    scrollSlidingTextTabStrip.setVisibility(View.GONE);
    actionBar.setExtraHeight(0);
  }
 else {
    scrollSlidingTextTabStrip.setVisibility(View.VISIBLE);
    actionBar.setExtraHeight(AndroidUtilities.dp(44));
  }
  int id=scrollSlidingTextTabStrip.getCurrentTabId();
  if (id >= 0) {
    mediaPages[0].selectedType=id;
  }
  scrollSlidingTextTabStrip.finishAddingTabs();
}
