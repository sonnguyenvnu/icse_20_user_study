private void checkChannelError(String text,int channelId){
switch (text) {
case "CHANNEL_PRIVATE":
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoCantLoad,channelId,0);
  break;
case "CHANNEL_PUBLIC_GROUP_NA":
NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoCantLoad,channelId,1);
break;
case "USER_BANNED_IN_CHANNEL":
NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.chatInfoCantLoad,channelId,2);
break;
}
}
