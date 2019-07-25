/** 
 * Creates a UrlEncodedQueryString by extracting and parsing the query component from the given URI. <p> This method assumes the query component is <code>www-form-urlencoded</code>. When parsing, all separators from the Separators enum are recognised. <p> The result of calling this method with a query component that is not <code>www-form-urlencoded</code> will likely be mismatched parameter names.
 * @param uri URI to be parsed
 */
public static UrlEncodedQueryStringBase parse(final URI uri){
  return parse(uri.getRawQuery());
}
