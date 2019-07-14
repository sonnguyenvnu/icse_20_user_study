private static String[] getActiveModuleProfiles(DeploymentDescriptor deploymentDescriptor){
  String[] activeModuleProfiles=new String[]{SofaModuleFrameworkConstants.DEFAULT_PROFILE_VALUE};
  String profiles=deploymentDescriptor.getProperty(SofaModuleFrameworkConstants.MODULE_PROFILE);
  if (profiles == null || profiles.length() == 0) {
    return activeModuleProfiles;
  }
  activeModuleProfiles=profiles.split(SofaModuleFrameworkConstants.PROFILE_SEPARATOR);
  return activeModuleProfiles;
}
