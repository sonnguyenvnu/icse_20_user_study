@Override public User createBlock(String screenName) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "blocks/create.json",new HttpParameter[]{new HttpParameter("screen_name",screenName)}));
}
