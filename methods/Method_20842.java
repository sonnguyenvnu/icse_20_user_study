/** 
 * Value to store in the ref tag cookie. Fits the template: {ref_tag} + {separator} + {time_of_setting}
 */
protected static @NonNull String cookieValueForRefTag(final @NonNull RefTag refTag){
  return refTag.tag() + COOKIE_VALUE_SEPARATOR + String.valueOf(SystemUtils.secondsSinceEpoch());
}
