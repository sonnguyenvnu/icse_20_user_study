@Override public void delete(final String name){
  GlobalConfiguration configs=loadGlobal();
  RegistryCenterConfiguration toBeRemovedConfig=find(name,configs.getRegistryCenterConfigurations());
  if (null != toBeRemovedConfig) {
    configs.getRegistryCenterConfigurations().getRegistryCenterConfiguration().remove(toBeRemovedConfig);
    configurationsXmlRepository.save(configs);
  }
}
