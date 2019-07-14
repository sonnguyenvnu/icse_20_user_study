@Override public User createMute(String screenName) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "mutes/users/create.json",new HttpParameter[]{new HttpParameter("screen_name",screenName)}));
}
