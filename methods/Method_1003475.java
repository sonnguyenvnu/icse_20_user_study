public static List<String> autocomplete(String input){
  String MAPS_API_KEY=BuildConfig.MAPS_API_KEY;
  if (TextUtils.isEmpty(MAPS_API_KEY)) {
    return Collections.emptyList();
  }
  ArrayList<String> resultList=null;
  HttpURLConnection conn=null;
  InputStreamReader in=null;
  StringBuilder jsonResults=new StringBuilder();
  try {
    URL url=new URL(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON + "?key=" + MAPS_API_KEY + "&input=" + URLEncoder.encode(input,"utf8"));
    conn=(HttpURLConnection)url.openConnection();
    in=new InputStreamReader(conn.getInputStream());
    int read;
    char[] buff=new char[1024];
    while ((read=in.read(buff)) != -1) {
      jsonResults.append(buff,0,read);
    }
  }
 catch (  MalformedURLException e) {
    LogDelegate.e("Error processing Places API URL");
    return null;
  }
catch (  IOException e) {
    LogDelegate.e("Error connecting to Places API");
    return null;
  }
 finally {
    if (conn != null) {
      conn.disconnect();
    }
    if (in != null) {
      try {
        in.close();
      }
 catch (      IOException e) {
        LogDelegate.e("Error closing address autocompletion InputStream");
      }
    }
  }
  try {
    JSONObject jsonObj=new JSONObject(jsonResults.toString());
    JSONArray predsJsonArray=jsonObj.getJSONArray("predictions");
    resultList=new ArrayList<>(predsJsonArray.length());
    for (int i=0; i < predsJsonArray.length(); i++) {
      resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
    }
  }
 catch (  JSONException e) {
    LogDelegate.e("Cannot process JSON results",e);
  }
 finally {
    if (conn != null) {
      conn.disconnect();
    }
    SystemHelper.closeCloseable(in);
  }
  return resultList;
}
