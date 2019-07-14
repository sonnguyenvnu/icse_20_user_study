public void setGlobalFixedDelayVariable(int milliseconds){
  GlobalSettings settings=globalSettingsHolder.get().copy().fixedDelay(milliseconds).build();
  updateGlobalSettings(settings);
}
