@Override public int hashCode(){
  return notification != null ? InputHelper.getSafeIntId(notification.getId()) : 0;
}
