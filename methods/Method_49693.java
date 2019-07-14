/** 
 * Handles intent URIs, extracts the query part and sends it to the search function. <p/> URIs may be of the form: <ul> <li> {@code geo:37.802,-122.41962}<li> {@code geo:37.802,-122.41962?q=7C66CM4X%2BC34&z=20}<li> {@code geo:0,0?q=WF59%2BX67%20Praia}</ul> <p/> Only the query string is used. Coordinates and zoom level are ignored. If the query string is not recognised by the search function (say, it's a street address), it will fail.
 */
private void handleGeoIntent(Intent intent){
  Uri uri=intent != null ? intent.getData() : null;
  if (uri == null) {
    return;
  }
  String schemeSpecificPart=uri.getEncodedSchemeSpecificPart();
  if (schemeSpecificPart == null || schemeSpecificPart.isEmpty()) {
    return;
  }
  int queryIndex=schemeSpecificPart.indexOf(URI_QUERY_SEPARATOR);
  if (queryIndex == -1) {
    return;
  }
  String searchQuery=schemeSpecificPart.substring(queryIndex + 2);
  if (searchQuery.contains(URI_ZOOM_SEPARATOR)) {
    searchQuery=searchQuery.substring(0,searchQuery.indexOf(URI_ZOOM_SEPARATOR));
  }
  final String searchString=Uri.decode(searchQuery);
  Log.i(TAG,"Search string is " + searchString);
  Handler h=new Handler();
  Runnable r=new Runnable(){
    @Override public void run(){
      if (mMainPresenter.getSearchActionsListener().searchCode(searchString)) {
        mMainPresenter.getSearchActionsListener().setSearchText(searchString);
      }
    }
  }
;
  h.postDelayed(r,2000);
}
