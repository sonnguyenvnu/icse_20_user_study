@ReactMethod public void getBatteryLevel(Promise p){
  Intent batteryIntent=this.reactContext.getApplicationContext().registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
  int level=batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
  int scale=batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
  float batteryLevel=level / (float)scale;
  p.resolve(batteryLevel);
}
