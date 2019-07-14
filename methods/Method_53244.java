User fillInIDAndScreenName(HttpParameter[] parameters) throws TwitterException {
  ensureAuthorizationEnabled();
  User user=new UserJSONImpl(http.get(conf.getRestBaseURL() + "account/verify_credentials.json",parameters,auth,this),conf);
  this.screenName=user.getScreenName();
  this.id=user.getId();
  return user;
}
