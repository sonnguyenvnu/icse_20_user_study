public void setSnoozeInterval(int interval){
  storage.putString("pref_snooze_interval",String.valueOf(interval));
}
