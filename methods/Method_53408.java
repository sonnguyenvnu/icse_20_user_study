@Override public TwitterAPIConfiguration getAPIConfiguration() throws TwitterException {
  return factory.createTwitterAPIConfiguration(get(conf.getRestBaseURL() + "help/configuration.json"));
}
