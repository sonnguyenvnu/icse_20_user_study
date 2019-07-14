public void setGlobalRandomDelayVariable(DelayDistribution distribution){
  GlobalSettings settings=globalSettingsHolder.get().copy().delayDistribution(distribution).build();
  updateGlobalSettings(settings);
}
