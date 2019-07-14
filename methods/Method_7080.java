public void postNotificationName(int id,Object... args){
  boolean allowDuringAnimation=false;
  if (allowedNotifications != null) {
    for (int a=0; a < allowedNotifications.length; a++) {
      if (allowedNotifications[a] == id) {
        allowDuringAnimation=true;
        break;
      }
    }
  }
  postNotificationNameInternal(id,allowDuringAnimation,args);
}
