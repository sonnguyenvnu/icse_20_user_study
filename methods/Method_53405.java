@Override public ResponseList<Location> getClosestTrends(GeoLocation location) throws TwitterException {
  return factory.createLocationList(get(conf.getRestBaseURL() + "trends/closest.json",new HttpParameter("lat",location.getLatitude()),new HttpParameter("long",location.getLongitude())));
}
