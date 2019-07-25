@Override public String encrypt(final User user){
  if (user == null) {
    throw new IllegalArgumentException("User can't be null");
  }
  if (Strings.isNullOrEmpty(user.getPassword())) {
    throw new IllegalArgumentException("User's password can't be null or empty");
  }
  return cache.getUnchecked(getPlainText(user));
}
