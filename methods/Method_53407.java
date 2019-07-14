@Override public User reportSpam(String screenName) throws TwitterException {
  return factory.createUser(post(conf.getRestBaseURL() + "users/report_spam.json",new HttpParameter[]{new HttpParameter("screen_name",screenName)}));
}
