@Override public Optional<User> authenticate(Cookie cookie){
  User user=null;
  if (cookie != null) {
    Optional<UserCookieData> cookieData=getUserCookieData(cookie);
    if (cookieData.isPresent()) {
      user=cookieData.get().getUser();
    }
  }
  return Optional.ofNullable(user);
}
