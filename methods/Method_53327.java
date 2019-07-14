@Override public User destroyBlock(String screenName) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "blocks/destroy.json",new HttpParameter[]{new HttpParameter("screen_name",screenName)}));
}
