@Override public void initEnvironment(ApplicationContext applicationContext){
  this.activeProfiles.clear();
  this.activeProfiles.add(SofaModuleFrameworkConstants.DEFAULT_PROFILE_VALUE);
  if (applicationContext == null || applicationContext.getEnvironment() == null) {
    return;
  }
  String activeProfiles=applicationContext.getBean(SofaModuleFrameworkConstants.SOFA_MODULE_PROPERTIES_BEAN_ID,SofaModuleProperties.class).getActiveProfiles();
  if (StringUtils.hasText(activeProfiles)) {
    String[] activeConfigProfileList=activeProfiles.split(SofaModuleFrameworkConstants.PROFILE_SEPARATOR);
    initActiveProfiles(activeConfigProfileList);
  }
}
