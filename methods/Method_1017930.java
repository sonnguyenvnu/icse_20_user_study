@Override public InspectionStatus inspect(Configuration configuration){
  InspectionStatus status=new InspectionStatus(false);
  JerseyRestClient restClient;
  try {
    restClient=new JerseyRestClient(jerseyClientConfig);
  }
 catch (  Exception e) {
    status.addError(String.format("Failed to parse Nifi properties: %s. Check values of configuration properties starting with 'nifi.rest' in '%s'",e.getMessage(),configuration.getServicesConfigLocation()));
    return status;
  }
  About response;
  try {
    response=restClient.get(NIFI_API_FLOW_ABOUT,About.class);
  }
 catch (  Exception e) {
    status.addError(String.format("Failed to connect to Nifi at '%s': %s. Check values of configuration properties starting with 'nifi.rest' in '%s'",jerseyClientConfig.getUrl(),e.getMessage(),configuration.getServicesConfigLocation()));
    return status;
  }
  String nifiVersion=response.about.version;
  status.addDescription(String.format("Successfully connected to Nifi version '%s' running at '%s'",nifiVersion,jerseyClientConfig.getUrl()));
  String nifiProfileKey=nifiVersion.substring(0,nifiVersion.lastIndexOf('.')) + ".x";
  List<String> nifiProfiles=nifiVersionsToProfiles.getOrDefault(nifiProfileKey,Collections.singletonList(DEFAULT_PROFILE));
  String expectedNifiProfile=NIFI_V + nifiProfiles.get(nifiProfiles.size() - 1);
  List<String> profiles=configuration.getServicesProfiles();
  List<String> selectedNifiProfiles=profiles.stream().filter(profile -> profile.startsWith(NIFI_V)).collect(Collectors.toList());
  if (selectedNifiProfiles.isEmpty()) {
    status.addError(String.format("Nifi profile is not set in '%s'. Add Nifi profile to '%s' property, e.g. '%s=<all-other-profiles>,%s'",configuration.getServicesConfigLocation(),SPRING_PROFILES_INCLUDE,SPRING_PROFILES_INCLUDE,expectedNifiProfile));
    return status;
  }
  if (selectedNifiProfiles.size() > 1) {
    status.addError(String.format("Found %s Nifi profiles in '%s'. Ensure only one Nifi profile is set, e.g. '%s=<all-other-profiles>,%s'",selectedNifiProfiles.size(),configuration.getServicesConfigLocation(),SPRING_PROFILES_INCLUDE,expectedNifiProfile));
    return status;
  }
  String selectedNifiProfile=selectedNifiProfiles.get(0);
  boolean isValidProfileSelected=nifiProfiles.stream().anyMatch(profile -> (NIFI_V + profile).equals(selectedNifiProfile));
  if (!isValidProfileSelected) {
    status.addError(String.format("Selected Nifi profile '%s' in '%s' doesn't match Nifi version '%s' running at '%s'. " + "Replace '%s' with '%s', eg. '%s=<all-other-profiles>,%s'",selectedNifiProfile,configuration.getServicesConfigLocation(),nifiVersion,jerseyClientConfig.getUrl(),selectedNifiProfile,expectedNifiProfile,SPRING_PROFILES_INCLUDE,expectedNifiProfile));
    return status;
  }
  status.setValid(true);
  return status;
}
