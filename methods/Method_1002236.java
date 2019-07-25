/** 
 * Creates a UrlEncodedQueryString by parsing the given query string. <p> This method assumes the given string is the <code>www-form-urlencoded</code> query component of a URI. When parsing, all <a href="UrlEncodedQueryString.Separator.html">Separators</a> are recognised. <p> The result of calling this method with a string that is not <code>www-form-urlencoded</code> (eg. passing an entire URI, not just its query string) will likely be mismatched parameter names.
 * @param query query string to be parsed
 */
public static UrlEncodedQueryStringBase parse(final CharSequence query){
  UrlEncodedQueryStringBase queryString=new UrlEncodedQueryStringBase();
  queryString.appendOrSet(query,true);
  return queryString;
}
