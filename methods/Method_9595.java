private void updateProfileData(){
  if (avatarImage == null || nameTextView == null) {
    return;
  }
  String onlineTextOverride;
  int currentConnectionState=ConnectionsManager.getInstance(currentAccount).getConnectionState();
  if (currentConnectionState == ConnectionsManager.ConnectionStateWaitingForNetwork) {
    onlineTextOverride=LocaleController.getString("WaitingForNetwork",R.string.WaitingForNetwork);
  }
 else   if (currentConnectionState == ConnectionsManager.ConnectionStateConnecting) {
    onlineTextOverride=LocaleController.getString("Connecting",R.string.Connecting);
  }
 else   if (currentConnectionState == ConnectionsManager.ConnectionStateUpdating) {
    onlineTextOverride=LocaleController.getString("Updating",R.string.Updating);
  }
 else   if (currentConnectionState == ConnectionsManager.ConnectionStateConnectingToProxy) {
    onlineTextOverride=LocaleController.getString("ConnectingToProxy",R.string.ConnectingToProxy);
  }
 else {
    onlineTextOverride=null;
  }
  if (user_id != 0) {
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(user_id);
    TLRPC.FileLocation photoBig=null;
    if (user.photo != null) {
      photoBig=user.photo.photo_big;
    }
    avatarDrawable.setInfo(user);
    avatarImage.setImage(ImageLocation.getForUser(user,false),"50_50",avatarDrawable,user);
    FileLoader.getInstance(currentAccount).loadFile(ImageLocation.getForUser(user,true),user,null,0,1);
    String newString=UserObject.getUserName(user);
    String newString2;
    if (user.id == UserConfig.getInstance(currentAccount).getClientUserId()) {
      newString2=LocaleController.getString("ChatYourSelf",R.string.ChatYourSelf);
      newString=LocaleController.getString("ChatYourSelfName",R.string.ChatYourSelfName);
    }
 else     if (user.id == 333000 || user.id == 777000 || user.id == 42777) {
      newString2=LocaleController.getString("ServiceNotifications",R.string.ServiceNotifications);
    }
 else     if (MessagesController.isSupportUser(user)) {
      newString2=LocaleController.getString("SupportStatus",R.string.SupportStatus);
    }
 else     if (isBot) {
      newString2=LocaleController.getString("Bot",R.string.Bot);
    }
 else {
      isOnline[0]=false;
      newString2=LocaleController.formatUserStatus(currentAccount,user,isOnline);
      if (onlineTextView[1] != null) {
        String key=isOnline[0] ? Theme.key_profile_status : Theme.key_avatar_subtitleInProfileBlue;
        onlineTextView[1].setTag(key);
        onlineTextView[1].setTextColor(Theme.getColor(key));
      }
    }
    for (int a=0; a < 2; a++) {
      if (nameTextView[a] == null) {
        continue;
      }
      if (a == 0 && user.id != UserConfig.getInstance(currentAccount).getClientUserId() && user.id / 1000 != 777 && user.id / 1000 != 333 && user.phone != null && user.phone.length() != 0 && ContactsController.getInstance(currentAccount).contactsDict.get(user.id) == null && (ContactsController.getInstance(currentAccount).contactsDict.size() != 0 || !ContactsController.getInstance(currentAccount).isLoadingContacts())) {
        String phoneString=PhoneFormat.getInstance().format("+" + user.phone);
        if (!nameTextView[a].getText().equals(phoneString)) {
          nameTextView[a].setText(phoneString);
        }
      }
 else {
        if (!nameTextView[a].getText().equals(newString)) {
          nameTextView[a].setText(newString);
        }
      }
      if (a == 0 && onlineTextOverride != null) {
        onlineTextView[a].setText(onlineTextOverride);
      }
 else {
        if (!onlineTextView[a].getText().equals(newString2)) {
          onlineTextView[a].setText(newString2);
        }
      }
      Drawable leftIcon=currentEncryptedChat != null ? Theme.chat_lockIconDrawable : null;
      Drawable rightIcon=null;
      if (a == 0) {
        if (user.scam) {
          rightIcon=getScamDrawable();
        }
 else {
          rightIcon=MessagesController.getInstance(currentAccount).isDialogMuted(dialog_id != 0 ? dialog_id : (long)user_id) ? Theme.chat_muteIconDrawable : null;
        }
      }
 else       if (user.scam) {
        rightIcon=getScamDrawable();
      }
 else       if (user.verified) {
        rightIcon=new CombinedDrawable(Theme.profile_verifiedDrawable,Theme.profile_verifiedCheckDrawable);
      }
      nameTextView[a].setLeftDrawable(leftIcon);
      nameTextView[a].setRightDrawable(rightIcon);
    }
    avatarImage.getImageReceiver().setVisible(!PhotoViewer.isShowingImage(photoBig),false);
  }
 else   if (chat_id != 0) {
    TLRPC.Chat chat=MessagesController.getInstance(currentAccount).getChat(chat_id);
    if (chat != null) {
      currentChat=chat;
    }
 else {
      chat=currentChat;
    }
    String newString;
    if (ChatObject.isChannel(chat)) {
      if (chatInfo == null || !currentChat.megagroup && (chatInfo.participants_count == 0 || ChatObject.hasAdminRights(currentChat) || chatInfo.can_view_participants)) {
        if (currentChat.megagroup) {
          newString=LocaleController.getString("Loading",R.string.Loading).toLowerCase();
        }
 else {
          if ((chat.flags & TLRPC.CHAT_FLAG_IS_PUBLIC) != 0) {
            newString=LocaleController.getString("ChannelPublic",R.string.ChannelPublic).toLowerCase();
          }
 else {
            newString=LocaleController.getString("ChannelPrivate",R.string.ChannelPrivate).toLowerCase();
          }
        }
      }
 else {
        if (currentChat.megagroup) {
          if (onlineCount > 1 && chatInfo.participants_count != 0) {
            newString=String.format("%s, %s",LocaleController.formatPluralString("Members",chatInfo.participants_count),LocaleController.formatPluralString("OnlineCount",Math.min(onlineCount,chatInfo.participants_count)));
          }
 else {
            newString=LocaleController.formatPluralString("Members",chatInfo.participants_count);
          }
        }
 else {
          int[] result=new int[1];
          String shortNumber=LocaleController.formatShortNumber(chatInfo.participants_count,result);
          if (currentChat.megagroup) {
            newString=LocaleController.formatPluralString("Members",chatInfo.participants_count);
          }
 else {
            newString=LocaleController.formatPluralString("Subscribers",chatInfo.participants_count);
          }
        }
      }
    }
 else {
      int count=chat.participants_count;
      if (chatInfo != null) {
        count=chatInfo.participants.participants.size();
      }
      if (count != 0 && onlineCount > 1) {
        newString=String.format("%s, %s",LocaleController.formatPluralString("Members",count),LocaleController.formatPluralString("OnlineCount",onlineCount));
      }
 else {
        newString=LocaleController.formatPluralString("Members",count);
      }
    }
    boolean changed=false;
    for (int a=0; a < 2; a++) {
      if (nameTextView[a] == null) {
        continue;
      }
      if (chat.title != null && !nameTextView[a].getText().equals(chat.title)) {
        if (nameTextView[a].setText(chat.title)) {
          changed=true;
        }
      }
      nameTextView[a].setLeftDrawable(null);
      if (a != 0) {
        if (chat.scam) {
          nameTextView[a].setRightDrawable(getScamDrawable());
        }
 else         if (chat.verified) {
          nameTextView[a].setRightDrawable(new CombinedDrawable(Theme.profile_verifiedDrawable,Theme.profile_verifiedCheckDrawable));
        }
 else {
          nameTextView[a].setRightDrawable(null);
        }
      }
 else {
        if (chat.scam) {
          nameTextView[a].setRightDrawable(getScamDrawable());
        }
 else {
          nameTextView[a].setRightDrawable(MessagesController.getInstance(currentAccount).isDialogMuted((long)-chat_id) ? Theme.chat_muteIconDrawable : null);
        }
      }
      if (a == 0 && onlineTextOverride != null) {
        onlineTextView[a].setText(onlineTextOverride);
      }
 else {
        if (currentChat.megagroup && chatInfo != null && onlineCount > 0) {
          if (!onlineTextView[a].getText().equals(newString)) {
            onlineTextView[a].setText(newString);
          }
        }
 else         if (a == 0 && ChatObject.isChannel(currentChat) && chatInfo != null && chatInfo.participants_count != 0 && (currentChat.megagroup || currentChat.broadcast)) {
          int[] result=new int[1];
          String shortNumber=LocaleController.formatShortNumber(chatInfo.participants_count,result);
          if (currentChat.megagroup) {
            onlineTextView[a].setText(LocaleController.formatPluralString("Members",result[0]).replace(String.format("%d",result[0]),shortNumber));
          }
 else {
            onlineTextView[a].setText(LocaleController.formatPluralString("Subscribers",result[0]).replace(String.format("%d",result[0]),shortNumber));
          }
        }
 else {
          if (!onlineTextView[a].getText().equals(newString)) {
            onlineTextView[a].setText(newString);
          }
        }
      }
    }
    if (changed) {
      needLayout();
    }
    TLRPC.FileLocation photoBig=null;
    if (chat.photo != null) {
      photoBig=chat.photo.photo_big;
    }
    avatarDrawable.setInfo(chat);
    avatarImage.setImage(ImageLocation.getForChat(chat,false),"50_50",avatarDrawable,chat);
    FileLoader.getInstance(currentAccount).loadFile(ImageLocation.getForChat(chat,true),chat,null,0,1);
    avatarImage.getImageReceiver().setVisible(!PhotoViewer.isShowingImage(photoBig),false);
  }
}
