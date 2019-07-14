private boolean checkChargingRequirement(Context context){
  if (!isChargingRequired()) {
    return true;
  }
  Intent batteryStatus=context.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
  if (batteryStatus == null) {
    return false;
  }
  int status=batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
  return status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
}
