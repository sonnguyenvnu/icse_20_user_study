public void saveConfig(boolean withFile,File oldFile){
synchronized (sync) {
    try {
      SharedPreferences.Editor editor=getPreferences().edit();
      if (currentAccount == 0) {
        editor.putInt("selectedAccount",selectedAccount);
      }
      editor.putBoolean("registeredForPush",registeredForPush);
      editor.putInt("lastSendMessageId",lastSendMessageId);
      editor.putInt("contactsSavedCount",contactsSavedCount);
      editor.putInt("lastBroadcastId",lastBroadcastId);
      editor.putBoolean("blockedUsersLoaded",blockedUsersLoaded);
      editor.putInt("lastContactsSyncTime",lastContactsSyncTime);
      editor.putInt("lastHintsSyncTime",lastHintsSyncTime);
      editor.putBoolean("draftsLoaded",draftsLoaded);
      editor.putBoolean("unreadDialogsLoaded",unreadDialogsLoaded);
      editor.putInt("ratingLoadTime",ratingLoadTime);
      editor.putInt("botRatingLoadTime",botRatingLoadTime);
      editor.putBoolean("contactsReimported",contactsReimported);
      editor.putInt("loginTime",loginTime);
      editor.putBoolean("syncContacts",syncContacts);
      editor.putBoolean("suggestContacts",suggestContacts);
      editor.putBoolean("hasSecureData",hasSecureData);
      editor.putBoolean("notificationsSettingsLoaded3",notificationsSettingsLoaded);
      editor.putBoolean("notificationsSignUpSettingsLoaded",notificationsSignUpSettingsLoaded);
      editor.putLong("autoDownloadConfigLoadTime",autoDownloadConfigLoadTime);
      editor.putBoolean("hasValidDialogLoadIds",hasValidDialogLoadIds);
      editor.putInt("6migrateOffsetId",migrateOffsetId);
      if (migrateOffsetId != -1) {
        editor.putInt("6migrateOffsetDate",migrateOffsetDate);
        editor.putInt("6migrateOffsetUserId",migrateOffsetUserId);
        editor.putInt("6migrateOffsetChatId",migrateOffsetChatId);
        editor.putInt("6migrateOffsetChannelId",migrateOffsetChannelId);
        editor.putLong("6migrateOffsetAccess",migrateOffsetAccess);
      }
      if (unacceptedTermsOfService != null) {
        try {
          SerializedData data=new SerializedData(unacceptedTermsOfService.getObjectSize());
          unacceptedTermsOfService.serializeToStream(data);
          String str=Base64.encodeToString(data.toByteArray(),Base64.DEFAULT);
          editor.putString("terms",str);
          data.cleanup();
        }
 catch (        Exception ignore) {
        }
      }
 else {
        editor.remove("terms");
      }
      if (currentAccount == 0) {
        if (pendingAppUpdate != null) {
          try {
            SerializedData data=new SerializedData(pendingAppUpdate.getObjectSize());
            pendingAppUpdate.serializeToStream(data);
            String str=Base64.encodeToString(data.toByteArray(),Base64.DEFAULT);
            editor.putString("appUpdate",str);
            editor.putInt("appUpdateBuild",pendingAppUpdateBuildVersion);
            editor.putLong("appUpdateTime",pendingAppUpdateInstallTime);
            editor.putLong("appUpdateCheckTime",lastUpdateCheckTime);
            data.cleanup();
          }
 catch (          Exception ignore) {
          }
        }
 else {
          editor.remove("appUpdate");
        }
      }
      SharedConfig.saveConfig();
      if (tmpPassword != null) {
        SerializedData data=new SerializedData();
        tmpPassword.serializeToStream(data);
        String string=Base64.encodeToString(data.toByteArray(),Base64.DEFAULT);
        editor.putString("tmpPassword",string);
        data.cleanup();
      }
 else {
        editor.remove("tmpPassword");
      }
      if (currentUser != null) {
        if (withFile) {
          SerializedData data=new SerializedData();
          currentUser.serializeToStream(data);
          String string=Base64.encodeToString(data.toByteArray(),Base64.DEFAULT);
          editor.putString("user",string);
          data.cleanup();
        }
      }
 else {
        editor.remove("user");
      }
      editor.commit();
      if (oldFile != null) {
        oldFile.delete();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
}
