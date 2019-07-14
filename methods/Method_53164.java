/** 
 * The Signature Base String includes the request absolute URL, tying the signature to a specific endpoint. The URL used in the Signature Base String MUST include the scheme, authority, and path, and MUST exclude the query and fragment as defined by [RFC3986] section 3.<br> If the absolute request URL is not available to the Service Provider (it is always available to the Consumer), it can be constructed by combining the scheme being used, the HTTP Host header, and the relative HTTP request URL. If the Host header is not available, the Service Provider SHOULD use the host name communicated to the Consumer in the documentation or other means.<br> The Service Provider SHOULD document the form of URL used in the Signature Base String to avoid ambiguity due to URL normalization. Unless specified, URL scheme and authority MUST be lowercase and include the port number; http default port 80 and https default port 443 MUST be excluded.<br> <br> For example, the request:<br> HTTP://Example.com:80/resource?id=123<br> Is included in the Signature Base String as:<br> http://example.com/resource
 * @param url the url to be normalized
 * @return the Signature Base String
 * @see <a href="http://oauth.net/core/1.0#rfc.section.9.1.2">OAuth Core - 9.1.2.  Construct Request URL</a>
 */
static String constructRequestURL(String url){
  int index=url.indexOf("?");
  if (-1 != index) {
    url=url.substring(0,index);
  }
  int slashIndex=url.indexOf("/",8);
  String baseURL=url.substring(0,slashIndex).toLowerCase();
  int colonIndex=baseURL.indexOf(":",8);
  if (-1 != colonIndex) {
    if (baseURL.startsWith("http://") && baseURL.endsWith(":80")) {
      baseURL=baseURL.substring(0,colonIndex);
    }
 else     if (baseURL.startsWith("https://") && baseURL.endsWith(":443")) {
      baseURL=baseURL.substring(0,colonIndex);
    }
  }
  url=baseURL + url.substring(slashIndex);
  return url;
}
