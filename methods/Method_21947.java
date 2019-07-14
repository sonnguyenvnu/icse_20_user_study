@Override public RegistryCenterConfiguration find(final String name,final RegistryCenterConfigurations configs){
  for (  RegistryCenterConfiguration each : configs.getRegistryCenterConfiguration()) {
    if (name.equals(each.getName())) {
      return each;
    }
  }
  return null;
}
