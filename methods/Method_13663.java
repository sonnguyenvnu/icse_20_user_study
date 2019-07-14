public List<String> getApplicationConfigurationDataIds(){
  List<String> applicationConfigurationDataIds=new ArrayList<>();
  if (!StringUtils.isEmpty(applicationGroup)) {
    applicationConfigurationDataIds.add(applicationGroup + ":" + applicationName + "." + acmProperties.getFileExtension());
    for (    String profile : activeProfiles) {
      applicationConfigurationDataIds.add(applicationGroup + ":" + applicationName + "-" + profile + "." + acmProperties.getFileExtension());
    }
  }
  applicationConfigurationDataIds.add(applicationName + "." + acmProperties.getFileExtension());
  for (  String profile : activeProfiles) {
    applicationConfigurationDataIds.add(applicationName + "-" + profile + "." + acmProperties.getFileExtension());
  }
  return applicationConfigurationDataIds;
}
