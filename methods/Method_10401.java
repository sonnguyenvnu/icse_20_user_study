protected final void debugStatusCode(String TAG,int statusCode){
  String msg=String.format(Locale.US,"Return Status Code: %d",statusCode);
  Log.d(TAG,msg);
  addView(getColoredView(LIGHTBLUE,msg));
}
