/** 
 * Removes query parameter from an Uri, if present.
 * @param uri The uri.
 * @param queryParameterName The name of the query parameter.
 * @return The uri without the query parameter.
 */
public static Uri removeQueryParameter(Uri uri,String queryParameterName){
  Uri.Builder builder=uri.buildUpon();
  builder.clearQuery();
  for (  String key : uri.getQueryParameterNames()) {
    if (!key.equals(queryParameterName)) {
      for (      String value : uri.getQueryParameters(key)) {
        builder.appendQueryParameter(key,value);
      }
    }
  }
  return builder.build();
}
