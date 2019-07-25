@Override public void finish(){
  NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
  nm.cancel(packageName.hashCode());
  super.finish();
}
