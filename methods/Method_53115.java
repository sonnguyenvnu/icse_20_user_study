static ResponseList<Trends> createTrendsList(HttpResponse res,boolean storeJSON) throws TwitterException {
  JSONObject json=res.asJSONObject();
  ResponseList<Trends> trends;
  try {
    Date asOf=ParseUtil.parseTrendsDate(json.getString("as_of"));
    JSONObject trendsJson=json.getJSONObject("trends");
    Location location=extractLocation(json,storeJSON);
    trends=new ResponseListImpl<Trends>(trendsJson.length(),res);
    Iterator ite=trendsJson.keys();
    while (ite.hasNext()) {
      String key=(String)ite.next();
      JSONArray array=trendsJson.getJSONArray(key);
      Trend[] trendsArray=jsonArrayToTrendArray(array,storeJSON);
      if (key.length() == 19) {
        trends.add(new TrendsJSONImpl(asOf,location,ParseUtil.getDate(key,"yyyy-MM-dd HH:mm:ss"),trendsArray));
      }
 else       if (key.length() == 16) {
        trends.add(new TrendsJSONImpl(asOf,location,ParseUtil.getDate(key,"yyyy-MM-dd HH:mm"),trendsArray));
      }
 else       if (key.length() == 10) {
        trends.add(new TrendsJSONImpl(asOf,location,ParseUtil.getDate(key,"yyyy-MM-dd"),trendsArray));
      }
    }
    Collections.sort(trends);
    return trends;
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone.getMessage() + ":" + res.asString(),jsone);
  }
}
