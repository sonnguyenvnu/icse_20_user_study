private static Location extractLocation(JSONObject json,boolean storeJSON) throws TwitterException {
  if (json.isNull("locations")) {
    return null;
  }
  ResponseList<Location> locations;
  try {
    locations=LocationJSONImpl.createLocationList(json.getJSONArray("locations"),storeJSON);
  }
 catch (  JSONException e) {
    throw new AssertionError("locations can't be null");
  }
  Location location;
  if (0 != locations.size()) {
    location=locations.get(0);
  }
 else {
    location=null;
  }
  return location;
}
