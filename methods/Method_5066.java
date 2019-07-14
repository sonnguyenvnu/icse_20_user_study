private boolean checkIdleRequirement(Context context){
  if (!isIdleRequired()) {
    return true;
  }
  PowerManager powerManager=(PowerManager)context.getSystemService(Context.POWER_SERVICE);
  return Util.SDK_INT >= 23 ? powerManager.isDeviceIdleMode() : Util.SDK_INT >= 20 ? !powerManager.isInteractive() : !powerManager.isScreenOn();
}
