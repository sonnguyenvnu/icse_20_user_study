@Override public User destroyMute(String screenName) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "mutes/users/destroy.json",new HttpParameter[]{new HttpParameter("screen_name",screenName)}));
}
