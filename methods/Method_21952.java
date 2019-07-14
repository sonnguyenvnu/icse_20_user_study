private GlobalConfiguration loadGlobal(){
  GlobalConfiguration result=configurationsXmlRepository.load();
  if (null == result.getRegistryCenterConfigurations()) {
    result.setRegistryCenterConfigurations(new RegistryCenterConfigurations());
  }
  return result;
}
