@Override public boolean add(final RegistryCenterConfiguration config){
  GlobalConfiguration configs=loadGlobal();
  boolean result=configs.getRegistryCenterConfigurations().getRegistryCenterConfiguration().add(config);
  if (result) {
    configurationsXmlRepository.save(configs);
  }
  return result;
}
