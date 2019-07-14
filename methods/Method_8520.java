public void updateSubtitle(){
  if (parentFragment == null) {
    return;
  }
  TLRPC.User user=parentFragment.getCurrentUser();
  if (UserObject.isUserSelf(user)) {
    if (subtitleTextView.getVisibility() != GONE) {
      subtitleTextView.setVisibility(GONE);
    }
    return;
  }
  TLRPC.Chat chat=parentFragment.getCurrentChat();
  CharSequence printString=MessagesController.getInstance(currentAccount).printingStrings.get(parentFragment.getDialogId());
  if (printString != null) {
    printString=TextUtils.replace(printString,new String[]{"..."},new String[]{""});
  }
  CharSequence newSubtitle;
  boolean useOnlineColor=false;
  if (printString == null || printString.length() == 0 || ChatObject.isChannel(chat) && !chat.megagroup) {
    setTypingAnimation(false);
    if (chat != null) {
      TLRPC.ChatFull info=parentFragment.getCurrentChatInfo();
      if (ChatObject.isChannel(chat)) {
        if (info != null && info.participants_count != 0) {
          if (chat.megagroup) {
            if (onlineCount > 1) {
              newSubtitle=String.format("%s, %s",LocaleController.formatPluralString("Members",info.participants_count),LocaleController.formatPluralString("OnlineCount",Math.min(onlineCount,info.participants_count)));
            }
 else {
              newSubtitle=LocaleController.formatPluralString("Members",info.participants_count);
            }
          }
 else {
            int[] result=new int[1];
            String shortNumber=LocaleController.formatShortNumber(info.participants_count,result);
            if (chat.megagroup) {
              newSubtitle=LocaleController.formatPluralString("Members",result[0]).replace(String.format("%d",result[0]),shortNumber);
            }
 else {
              newSubtitle=LocaleController.formatPluralString("Subscribers",result[0]).replace(String.format("%d",result[0]),shortNumber);
            }
          }
        }
 else {
          if (chat.megagroup) {
            newSubtitle=LocaleController.getString("Loading",R.string.Loading).toLowerCase();
          }
 else {
            if ((chat.flags & TLRPC.CHAT_FLAG_IS_PUBLIC) != 0) {
              newSubtitle=LocaleController.getString("ChannelPublic",R.string.ChannelPublic).toLowerCase();
            }
 else {
              newSubtitle=LocaleController.getString("ChannelPrivate",R.string.ChannelPrivate).toLowerCase();
            }
          }
        }
      }
 else {
        if (ChatObject.isKickedFromChat(chat)) {
          newSubtitle=LocaleController.getString("YouWereKicked",R.string.YouWereKicked);
        }
 else         if (ChatObject.isLeftFromChat(chat)) {
          newSubtitle=LocaleController.getString("YouLeft",R.string.YouLeft);
        }
 else {
          int count=chat.participants_count;
          if (info != null && info.participants != null) {
            count=info.participants.participants.size();
          }
          if (onlineCount > 1 && count != 0) {
            newSubtitle=String.format("%s, %s",LocaleController.formatPluralString("Members",count),LocaleController.formatPluralString("OnlineCount",onlineCount));
          }
 else {
            newSubtitle=LocaleController.formatPluralString("Members",count);
          }
        }
      }
    }
 else     if (user != null) {
      TLRPC.User newUser=MessagesController.getInstance(currentAccount).getUser(user.id);
      if (newUser != null) {
        user=newUser;
      }
      String newStatus;
      if (user.id == UserConfig.getInstance(currentAccount).getClientUserId()) {
        newStatus=LocaleController.getString("ChatYourSelf",R.string.ChatYourSelf);
      }
 else       if (user.id == 333000 || user.id == 777000 || user.id == 42777) {
        newStatus=LocaleController.getString("ServiceNotifications",R.string.ServiceNotifications);
      }
 else       if (MessagesController.isSupportUser(user)) {
        newStatus=LocaleController.getString("SupportStatus",R.string.SupportStatus);
      }
 else       if (user.bot) {
        newStatus=LocaleController.getString("Bot",R.string.Bot);
      }
 else {
        isOnline[0]=false;
        newStatus=LocaleController.formatUserStatus(currentAccount,user,isOnline);
        useOnlineColor=isOnline[0];
      }
      newSubtitle=newStatus;
    }
 else {
      newSubtitle="";
    }
  }
 else {
    newSubtitle=printString;
    useOnlineColor=true;
    setTypingAnimation(true);
  }
  if (lastSubtitle == null) {
    subtitleTextView.setText(newSubtitle);
    String key=useOnlineColor ? Theme.key_chat_status : Theme.key_actionBarDefaultSubtitle;
    subtitleTextView.setTextColor(Theme.getColor(key));
    subtitleTextView.setTag(key);
  }
 else {
    lastSubtitle=newSubtitle;
  }
}
