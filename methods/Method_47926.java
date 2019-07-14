public long getSnoozeInterval(){
  return Long.parseLong(storage.getString("pref_snooze_interval","15"));
}
