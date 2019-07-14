public void setNotificationsSticky(boolean sticky){
  storage.putBoolean("pref_sticky_notifications",sticky);
  for (  Listener l : listeners)   l.onNotificationsChanged();
}
