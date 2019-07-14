@Override public String getScreenName() throws TwitterException, IllegalStateException {
  if (!auth.isEnabled()) {
    throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
  }
  if (null == screenName) {
    if (auth instanceof BasicAuthorization) {
      screenName=((BasicAuthorization)auth).getUserId();
      if (screenName.contains("@")) {
        screenName=null;
      }
    }
    if (null == screenName) {
      fillInIDAndScreenName();
    }
  }
  return screenName;
}
