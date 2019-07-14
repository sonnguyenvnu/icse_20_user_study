@Override public User showUser(String screenName) throws TwitterException {
  return factory.createUser(get(conf.getRestBaseURL() + "users/show.json",new HttpParameter[]{new HttpParameter("screen_name",screenName)}));
}
