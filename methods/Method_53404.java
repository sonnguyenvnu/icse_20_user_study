@Override public ResponseList<Location> getAvailableTrends() throws TwitterException {
  return factory.createLocationList(get(conf.getRestBaseURL() + "trends/available.json"));
}
