@Override public boolean acceptsProfiles(String[] sofaModuleProfiles){
  Assert.notEmpty(sofaModuleProfiles,"Must specify at least one sofa module profile,at least one profile value is " + SofaModuleFrameworkConstants.DEFAULT_PROFILE_VALUE);
  for (  String sofaModuleProfile : sofaModuleProfiles) {
    if (StringUtils.hasText(sofaModuleProfile) && sofaModuleProfile.charAt(0) == '!') {
      if (!isProfileActive(sofaModuleProfile.substring(1))) {
        return true;
      }
    }
 else     if (isProfileActive(sofaModuleProfile)) {
      return true;
    }
  }
  return false;
}
