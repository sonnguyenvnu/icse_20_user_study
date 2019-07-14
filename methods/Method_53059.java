static GeoLocation[][] coordinatesAsGeoLocationArray(JSONArray coordinates) throws TwitterException {
  try {
    GeoLocation[][] boundingBox=new GeoLocation[coordinates.length()][];
    for (int i=0; i < coordinates.length(); i++) {
      JSONArray array=coordinates.getJSONArray(i);
      boundingBox[i]=new GeoLocation[array.length()];
      for (int j=0; j < array.length(); j++) {
        JSONArray coordinate=array.getJSONArray(j);
        boundingBox[i][j]=new GeoLocation(coordinate.getDouble(1),coordinate.getDouble(0));
      }
    }
    return boundingBox;
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
