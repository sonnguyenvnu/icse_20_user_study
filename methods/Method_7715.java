public static void applyProfileTheme(){
  if (profile_verifiedDrawable == null) {
    return;
  }
  profile_aboutTextPaint.setColor(getColor(key_windowBackgroundWhiteBlackText));
  profile_aboutTextPaint.linkColor=getColor(key_windowBackgroundWhiteLinkText);
  setDrawableColorByKey(profile_verifiedDrawable,key_profile_verifiedBackground);
  setDrawableColorByKey(profile_verifiedCheckDrawable,key_profile_verifiedCheck);
}
