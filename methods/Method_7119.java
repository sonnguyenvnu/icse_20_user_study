public void setGlobalNotificationsEnabled(int type,int time){
  MessagesController.getNotificationsSettings(currentAccount).edit().putInt(getGlobalNotificationsKey(type),time).commit();
  NotificationsController.getInstance(currentAccount).updateServerNotificationsSettings(type);
}
