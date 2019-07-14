public void setNotificationsLed(boolean enabled){
  storage.putBoolean("pref_led_notifications",enabled);
  for (  Listener l : listeners)   l.onNotificationsChanged();
}
