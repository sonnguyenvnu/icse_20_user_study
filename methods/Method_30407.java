public static PendingIntent makePendingIntent(Context context,@MediaKeyAction long action){
  int keyCode=PlaybackStateCompat.toKeyCode(action);
  if (keyCode == KeyEvent.KEYCODE_UNKNOWN) {
    LogUtils.e("Cannot build a media button pending intent with the given action: " + action);
    return null;
  }
  Intent intent=new Intent(context,MediaButtonReceiver.class).setAction(Intent.ACTION_MEDIA_BUTTON).putExtra(Intent.EXTRA_KEY_EVENT,new KeyEvent(KeyEvent.ACTION_DOWN,keyCode));
  return PendingIntent.getBroadcast(context,keyCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
}
