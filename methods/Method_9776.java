/** 
 * Checks whether the specified user URL is invalid.
 * @param userURL the specified user URL
 * @return {@code true} if it is invalid, returns {@code false} otherwise
 */
private boolean invalidUserURL(final String userURL){
  if (!Strings.isURL(userURL)) {
    return true;
  }
  return userURL.length() > MAX_USER_URL_LENGTH;
}
