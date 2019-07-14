/** 
 * Returns whether the user's location setting is in Germany.
 */
public static boolean isLocationGermany(final @NonNull User user){
  final Location location=user.location();
  if (location == null) {
    return false;
  }
  return I18nUtils.isCountryGermany(location.country());
}
