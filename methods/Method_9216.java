private void perfromSelectedDialogsAction(int action,boolean alert){
  if (getParentActivity() == null) {
    return;
  }
  ArrayList<Long> selectedDialogs=dialogsAdapter.getSelectedDialogs();
  int count=selectedDialogs.size();
  if (action == archive) {
    ArrayList<Long> copy=new ArrayList<>(selectedDialogs);
    getMessagesController().addDialogToFolder(copy,folderId == 0 ? 1 : 0,-1,null,0);
    hideActionMode(false);
    if (folderId == 0) {
      SharedPreferences preferences=MessagesController.getGlobalMainSettings();
      boolean hintShowed=preferences.getBoolean("archivehint_l",false);
      preferences.edit().putBoolean("archivehint_l",true).commit();
      int undoAction;
      if (hintShowed) {
        undoAction=copy.size() > 1 ? UndoView.ACTION_ARCHIVE_FEW : UndoView.ACTION_ARCHIVE;
      }
 else {
        undoAction=copy.size() > 1 ? UndoView.ACTION_ARCHIVE_FEW_HINT : UndoView.ACTION_ARCHIVE_HINT;
      }
      getUndoView().showWithAction(0,undoAction,null,() -> getMessagesController().addDialogToFolder(copy,folderId == 0 ? 0 : 1,-1,null,0));
    }
 else {
      ArrayList<TLRPC.Dialog> dialogs=getMessagesController().getDialogs(folderId);
      if (dialogs.isEmpty()) {
        listView.setEmptyView(null);
        progressView.setVisibility(View.INVISIBLE);
        finishFragment();
      }
    }
    return;
  }
 else   if (action == pin && canPinCount != 0) {
    int pinnedCount=0;
    int pinnedSecretCount=0;
    int newPinnedCount=0;
    int newPinnedSecretCount=0;
    ArrayList<TLRPC.Dialog> dialogs=getMessagesController().getDialogs(folderId);
    for (int a=0, N=dialogs.size(); a < N; a++) {
      TLRPC.Dialog dialog=dialogs.get(a);
      if (dialog instanceof TLRPC.TL_dialogFolder) {
        continue;
      }
      int lower_id=(int)dialog.id;
      if (dialog.pinned) {
        if (lower_id == 0) {
          pinnedSecretCount++;
        }
 else {
          pinnedCount++;
        }
      }
 else {
        break;
      }
    }
    for (int a=0; a < count; a++) {
      long selectedDialog=selectedDialogs.get(a);
      TLRPC.Dialog dialog=getMessagesController().dialogs_dict.get(selectedDialog);
      if (dialog == null || dialog.pinned) {
        continue;
      }
      int lower_id=(int)selectedDialog;
      if (lower_id == 0) {
        newPinnedSecretCount++;
      }
 else {
        newPinnedCount++;
      }
    }
    int maxPinnedCount;
    if (folderId != 0) {
      maxPinnedCount=getMessagesController().maxFolderPinnedDialogsCount;
    }
 else {
      maxPinnedCount=getMessagesController().maxPinnedDialogsCount;
    }
    if (newPinnedSecretCount + pinnedSecretCount > maxPinnedCount || newPinnedCount + pinnedCount > maxPinnedCount) {
      AlertsCreator.showSimpleToast(DialogsActivity.this,LocaleController.formatString("PinToTopLimitReached",R.string.PinToTopLimitReached,LocaleController.formatPluralString("Chats",maxPinnedCount)));
      AndroidUtilities.shakeView(pinItem,2,0);
      Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
      if (v != null) {
        v.vibrate(200);
      }
      return;
    }
  }
 else   if ((action == delete || action == clear) && count > 1 && alert) {
    if (alert) {
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      if (action == delete) {
        builder.setTitle(LocaleController.formatString("DeleteFewChatsTitle",R.string.DeleteFewChatsTitle,LocaleController.formatPluralString("ChatsSelected",count)));
        builder.setMessage(LocaleController.getString("AreYouSureDeleteFewChats",R.string.AreYouSureDeleteFewChats));
        builder.setPositiveButton(LocaleController.getString("Delete",R.string.Delete),(dialog1,which) -> {
          getMessagesController().setDialogsInTransaction(true);
          perfromSelectedDialogsAction(action,false);
          getMessagesController().setDialogsInTransaction(false);
          MessagesController.getInstance(currentAccount).checkIfFolderEmpty(folderId);
          if (folderId != 0 && getDialogsArray(currentAccount,dialogsType,folderId,false).size() == 0) {
            listView.setEmptyView(null);
            progressView.setVisibility(View.INVISIBLE);
            finishFragment();
          }
        }
);
      }
 else {
        if (canClearCacheCount != 0) {
          builder.setTitle(LocaleController.formatString("ClearCacheFewChatsTitle",R.string.ClearCacheFewChatsTitle,LocaleController.formatPluralString("ChatsSelectedClearCache",count)));
          builder.setMessage(LocaleController.getString("AreYouSureClearHistoryCacheFewChats",R.string.AreYouSureClearHistoryCacheFewChats));
          builder.setPositiveButton(LocaleController.getString("ClearHistoryCache",R.string.ClearHistoryCache),(dialog1,which) -> perfromSelectedDialogsAction(action,false));
        }
 else {
          builder.setTitle(LocaleController.formatString("ClearFewChatsTitle",R.string.ClearFewChatsTitle,LocaleController.formatPluralString("ChatsSelectedClear",count)));
          builder.setMessage(LocaleController.getString("AreYouSureClearHistoryFewChats",R.string.AreYouSureClearHistoryFewChats));
          builder.setPositiveButton(LocaleController.getString("ClearHistory",R.string.ClearHistory),(dialog1,which) -> perfromSelectedDialogsAction(action,false));
        }
      }
      builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
      AlertDialog alertDialog=builder.create();
      showDialog(alertDialog);
      TextView button=(TextView)alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
      if (button != null) {
        button.setTextColor(Theme.getColor(Theme.key_dialogTextRed2));
      }
      return;
    }
  }
  boolean scrollToTop=false;
  for (int a=0; a < count; a++) {
    long selectedDialog=selectedDialogs.get(a);
    TLRPC.Dialog dialog=getMessagesController().dialogs_dict.get(selectedDialog);
    if (dialog == null) {
      continue;
    }
    TLRPC.Chat chat;
    TLRPC.User user=null;
    int lower_id=(int)selectedDialog;
    int high_id=(int)(selectedDialog >> 32);
    if (lower_id != 0) {
      if (lower_id > 0) {
        user=getMessagesController().getUser(lower_id);
        chat=null;
      }
 else {
        chat=getMessagesController().getChat(-lower_id);
      }
    }
 else {
      TLRPC.EncryptedChat encryptedChat=getMessagesController().getEncryptedChat(high_id);
      chat=null;
      if (encryptedChat != null) {
        user=getMessagesController().getUser(encryptedChat.user_id);
      }
 else {
        user=new TLRPC.TL_userEmpty();
      }
    }
    if (chat == null && user == null) {
      continue;
    }
    boolean isBot=user != null && user.bot && !MessagesController.isSupportUser(user);
    if (action == pin) {
      if (canPinCount != 0) {
        if (dialog.pinned) {
          continue;
        }
        if (getMessagesController().pinDialog(selectedDialog,true,null,-1)) {
          scrollToTop=true;
        }
      }
 else {
        if (!dialog.pinned) {
          continue;
        }
        if (getMessagesController().pinDialog(selectedDialog,false,null,-1)) {
          scrollToTop=true;
        }
      }
    }
 else     if (action == read) {
      if (canReadCount != 0) {
        getMessagesController().markMentionsAsRead(selectedDialog);
        getMessagesController().markDialogAsRead(selectedDialog,dialog.top_message,dialog.top_message,dialog.last_message_date,false,0,true);
      }
 else {
        getMessagesController().markDialogAsUnread(selectedDialog,null,0);
      }
    }
 else     if (action == delete || action == clear) {
      if (count == 1) {
        AlertsCreator.createClearOrDeleteDialogAlert(DialogsActivity.this,action == clear,chat,user,lower_id == 0,(param) -> {
          hideActionMode(false);
          if (action == clear && ChatObject.isChannel(chat) && (!chat.megagroup || !TextUtils.isEmpty(chat.username))) {
            getMessagesController().deleteDialog(selectedDialog,2,param);
          }
 else {
            if (action == delete && folderId != 0 && getDialogsArray(currentAccount,dialogsType,folderId,false).size() == 1) {
              progressView.setVisibility(View.INVISIBLE);
            }
            getUndoView().showWithAction(selectedDialog,action == clear ? UndoView.ACTION_CLEAR : UndoView.ACTION_DELETE,() -> {
              if (action == clear) {
                getMessagesController().deleteDialog(selectedDialog,1,param);
              }
 else {
                if (chat != null) {
                  if (ChatObject.isNotInChat(chat)) {
                    getMessagesController().deleteDialog(selectedDialog,0,param);
                  }
 else {
                    TLRPC.User currentUser=getMessagesController().getUser(getUserConfig().getClientUserId());
                    getMessagesController().deleteUserFromChat((int)-selectedDialog,currentUser,null);
                  }
                }
 else {
                  getMessagesController().deleteDialog(selectedDialog,0,param);
                  if (isBot) {
                    getMessagesController().blockUser((int)selectedDialog);
                  }
                }
                if (AndroidUtilities.isTablet()) {
                  getNotificationCenter().postNotificationName(NotificationCenter.closeChats,selectedDialog);
                }
                MessagesController.getInstance(currentAccount).checkIfFolderEmpty(folderId);
              }
            }
);
          }
        }
);
        return;
      }
 else {
        if (action == clear && canClearCacheCount != 0) {
          getMessagesController().deleteDialog(selectedDialog,2,false);
        }
 else {
          if (action == clear) {
            getMessagesController().deleteDialog(selectedDialog,1,false);
          }
 else {
            if (chat != null) {
              if (ChatObject.isNotInChat(chat)) {
                getMessagesController().deleteDialog(selectedDialog,0,false);
              }
 else {
                TLRPC.User currentUser=getMessagesController().getUser(getUserConfig().getClientUserId());
                getMessagesController().deleteUserFromChat((int)-selectedDialog,currentUser,null);
              }
            }
 else {
              getMessagesController().deleteDialog(selectedDialog,0,false);
              if (isBot) {
                getMessagesController().blockUser((int)selectedDialog);
              }
            }
            if (AndroidUtilities.isTablet()) {
              getNotificationCenter().postNotificationName(NotificationCenter.closeChats,selectedDialog);
            }
          }
        }
      }
    }
 else     if (action == mute) {
      if (count == 1 && canMuteCount == 1) {
        showDialog(AlertsCreator.createMuteAlert(getParentActivity(),selectedDialog),dialog12 -> hideActionMode(true));
        return;
      }
 else {
        if (canUnmuteCount != 0) {
          if (!getMessagesController().isDialogMuted(selectedDialog)) {
            continue;
          }
          getNotificationsController().setDialogNotificationsSettings(selectedDialog,NotificationsController.SETTING_MUTE_UNMUTE);
        }
 else {
          if (getMessagesController().isDialogMuted(selectedDialog)) {
            continue;
          }
          getNotificationsController().setDialogNotificationsSettings(selectedDialog,NotificationsController.SETTING_MUTE_FOREVER);
        }
      }
    }
  }
  if (action == pin) {
    getMessagesController().reorderPinnedDialogs(folderId,null,0);
  }
  if (scrollToTop) {
    hideFloatingButton(false);
    listView.smoothScrollToPosition(hasHiddenArchive() ? 1 : 0);
  }
  hideActionMode(action != pin && action != delete);
}
