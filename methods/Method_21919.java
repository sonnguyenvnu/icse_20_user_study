private void loadActivatedRegCenter(final HttpSession httpSession){
  Optional<RegistryCenterConfiguration> config=regCenterService.loadActivated();
  if (config.isPresent()) {
    String configName=config.get().getName();
    boolean isConnected=setRegistryCenterNameToSession(regCenterService.find(configName,regCenterService.loadAll()),httpSession);
    if (isConnected) {
      regCenterService.load(configName);
    }
  }
}
