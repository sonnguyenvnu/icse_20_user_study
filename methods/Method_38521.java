/** 
 * Parses 'location' header to return the next location or returns  {@code null} if location not specified.Specification (<a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.30">rfc2616</a>) says that only absolute path must be provided, however, this does not happens in the real world. There a <a href="https://tools.ietf.org/html/rfc7231#section-7.1.2">proposal</a> that allows server name etc to be omitted.
 */
public String location(){
  String location=header("location");
  if (location == null) {
    return null;
  }
  if (location.startsWith(StringPool.SLASH)) {
    location=getHttpRequest().hostUrl() + location;
  }
  return location;
}
