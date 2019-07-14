public boolean shouldMakeNotificationsLed(){
  return storage.getBoolean("pref_led_notifications",false);
}
