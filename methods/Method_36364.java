private void validateProfile(String profile){
  if (!StringUtils.hasText(profile)) {
    throw new IllegalArgumentException("Invalid profile [" + profile + "]: must contain text and at least a value " + SofaModuleFrameworkConstants.DEFAULT_PROFILE_VALUE);
  }
  if (profile.charAt(0) == '!') {
    throw new IllegalArgumentException("Invalid sofa.profiles.active value in sofa-config.config [" + profile + "]: must not begin with ! operator");
  }
}
