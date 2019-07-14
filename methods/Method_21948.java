private void setActivated(final GlobalConfiguration configs,final RegistryCenterConfiguration toBeConnectedConfig){
  RegistryCenterConfiguration activatedConfig=findActivatedRegistryCenterConfiguration(configs);
  if (!toBeConnectedConfig.equals(activatedConfig)) {
    if (null != activatedConfig) {
      activatedConfig.setActivated(false);
    }
    toBeConnectedConfig.setActivated(true);
    configurationsXmlRepository.save(configs);
  }
}
