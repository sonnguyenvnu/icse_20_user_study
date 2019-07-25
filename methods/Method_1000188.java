public static void reload(String reason,Context context,boolean interactive){
  SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
  if (prefs.getBoolean("enabled",false)) {
    Intent intent=new Intent(context,ServiceSinkhole.class);
    intent.putExtra(EXTRA_COMMAND,Command.reload);
    intent.putExtra(EXTRA_REASON,reason);
    intent.putExtra(EXTRA_INTERACTIVE,interactive);
    ContextCompat.startForegroundService(context,intent);
  }
}
