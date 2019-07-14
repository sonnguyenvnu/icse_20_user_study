@Override public ResponseList<Place> searchPlaces(GeoQuery query) throws TwitterException {
  return factory.createPlaceList(get(conf.getRestBaseURL() + "geo/search.json",query.asHttpParameterArray()));
}
