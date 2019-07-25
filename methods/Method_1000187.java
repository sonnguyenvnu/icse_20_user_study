private void set(Intent intent){
  int uid=intent.getIntExtra(EXTRA_UID,0);
  String network=intent.getStringExtra(EXTRA_NETWORK);
  String pkg=intent.getStringExtra(EXTRA_PACKAGE);
  boolean blocked=intent.getBooleanExtra(EXTRA_BLOCKED,false);
  Log.i(TAG,"Set " + pkg + " " + network + "=" + blocked);
  SharedPreferences settings=PreferenceManager.getDefaultSharedPreferences(ServiceSinkhole.this);
  boolean default_wifi=settings.getBoolean("whitelist_wifi",true);
  boolean default_other=settings.getBoolean("whitelist_other",true);
  SharedPreferences prefs=getSharedPreferences(network,Context.MODE_PRIVATE);
  if (blocked == ("wifi".equals(network) ? default_wifi : default_other))   prefs.edit().remove(pkg).apply();
 else   prefs.edit().putBoolean(pkg,blocked).apply();
  ServiceSinkhole.reload("notification",ServiceSinkhole.this,false);
  notifyNewApplication(uid);
  Intent ruleset=new Intent(ActivityMain.ACTION_RULES_CHANGED);
  LocalBroadcastManager.getInstance(ServiceSinkhole.this).sendBroadcast(ruleset);
}
