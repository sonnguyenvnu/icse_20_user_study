private static void setFlashlight(boolean active){
  if (iHardwareService != null) {
    invoke(setFlashEnabledMethod,iHardwareService,active);
  }
}
