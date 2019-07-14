/** 
 * canonicalizeUrl <br> Borrowed from Jsoup.
 * @param url url
 * @param refer refer
 * @return canonicalizeUrl
 */
public static String canonicalizeUrl(String url,String refer){
  URL base;
  try {
    try {
      base=new URL(refer);
    }
 catch (    MalformedURLException e) {
      URL abs=new URL(refer);
      return abs.toExternalForm();
    }
    if (url.startsWith("?"))     url=base.getPath() + url;
    URL abs=new URL(base,url);
    return abs.toExternalForm();
  }
 catch (  MalformedURLException e) {
    return "";
  }
}
