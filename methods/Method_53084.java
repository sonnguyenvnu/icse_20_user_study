static ResponseList<Location> createLocationList(JSONArray list,boolean storeJSON) throws TwitterException {
  try {
    int size=list.length();
    ResponseList<Location> locations=new ResponseListImpl<Location>(size,null);
    for (int i=0; i < size; i++) {
      JSONObject json=list.getJSONObject(i);
      Location location=new LocationJSONImpl(json);
      locations.add(location);
      if (storeJSON) {
        TwitterObjectFactory.registerJSONObject(location,json);
      }
    }
    if (storeJSON) {
      TwitterObjectFactory.registerJSONObject(locations,list);
    }
    return locations;
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
