private void initActiveProfiles(String[] ActiveProfiles){
  if (ActiveProfiles == null || ActiveProfiles.length == 0) {
    return;
  }
  for (  String sofaProfile : ActiveProfiles) {
    validateProfile(sofaProfile);
    this.activeProfiles.add(sofaProfile.trim());
  }
}
