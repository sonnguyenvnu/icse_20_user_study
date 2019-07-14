@Override public Place getGeoDetails(String placeId) throws TwitterException {
  return factory.createPlace(get(conf.getRestBaseURL() + "geo/id/" + placeId + ".json"));
}
