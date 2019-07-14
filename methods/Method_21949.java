private RegistryCenterConfiguration findActivatedRegistryCenterConfiguration(final GlobalConfiguration configs){
  for (  RegistryCenterConfiguration each : configs.getRegistryCenterConfigurations().getRegistryCenterConfiguration()) {
    if (each.isActivated()) {
      return each;
    }
  }
  return null;
}
