@Override public ResponseList<Place> createPlaceList(final HttpResponse res) throws TwitterException {
  return new LazyResponseList<Place>(){
    @Override protected ResponseList<Place> createActualResponseList() throws TwitterException {
      return PlaceJSONImpl.createPlaceList(res,conf);
    }
  }
;
}
