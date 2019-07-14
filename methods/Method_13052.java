@Override public boolean isActivated(){
  return !PlatformService.getInstance().isMac();
}
