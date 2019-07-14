@Override public ResponseList<Place> createPlaceList(HttpResponse res) throws TwitterException {
  try {
    return PlaceJSONImpl.createPlaceList(res,conf);
  }
 catch (  TwitterException te) {
    if (te.getStatusCode() == 404) {
      return new ResponseListImpl<Place>(0,null);
    }
 else {
      throw te;
    }
  }
}
