@Override public ResponseList<Place> reverseGeoCode(GeoQuery query) throws TwitterException {
  try {
    return factory.createPlaceList(get(conf.getRestBaseURL() + "geo/reverse_geocode.json",query.asHttpParameterArray()));
  }
 catch (  TwitterException te) {
    if (te.getStatusCode() == 404) {
      return factory.createEmptyResponseList();
    }
 else {
      throw te;
    }
  }
}
