public static boolean isUSUserViewingNonUSProject(final @NonNull String userCountry,final @NonNull String projectCountry){
  return I18nUtils.isCountryUS(userCountry) && !I18nUtils.isCountryUS(projectCountry);
}
