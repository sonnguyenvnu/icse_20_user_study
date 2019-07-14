@ReactMethod public void isBatteryCharging(Promise p){
  IntentFilter ifilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
  Intent batteryStatus=this.reactContext.getApplicationContext().registerReceiver(null,ifilter);
  int status=batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
  boolean isCharging=status == BatteryManager.BATTERY_STATUS_CHARGING;
  p.resolve(isCharging);
}
