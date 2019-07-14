/** 
 * Determine if any of checks profiles is among enabled profiles.
 */
protected boolean matchProfiles(final String[] checkProfiles){
  if ((checkProfiles != null) && (checkProfiles.length == 1) && checkProfiles[0].equals(ALL_PROFILES)) {
    return true;
  }
  if (enabledProfiles == null || enabledProfiles.isEmpty()) {
    if (validateAllProfilesByDefault) {
      return true;
    }
    if ((checkProfiles == null) || (checkProfiles.length == 0)) {
      return true;
    }
    for (    String profile : checkProfiles) {
      if (StringUtil.isEmpty(profile)) {
        return true;
      }
      if (profile.equals(DEFAULT_PROFILE)) {
        return true;
      }
    }
    return false;
  }
  if ((checkProfiles == null) || (checkProfiles.length == 0)) {
    return enabledProfiles.contains(DEFAULT_PROFILE);
  }
  boolean result=false;
  for (  String profile : checkProfiles) {
    boolean b=true;
    boolean must=false;
    if (StringUtil.isEmpty(profile)) {
      profile=DEFAULT_PROFILE;
    }
 else     if (profile.charAt(0) == '-') {
      profile=profile.substring(1);
      b=false;
    }
 else     if (profile.charAt(0) == '+') {
      profile=profile.substring(1);
      must=true;
    }
    if (enabledProfiles.contains(profile)) {
      if (!b) {
        return false;
      }
      result=true;
    }
 else {
      if (must) {
        return false;
      }
    }
  }
  return result;
}
