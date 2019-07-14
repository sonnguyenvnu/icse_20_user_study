/** 
 * Gets link from the specified URL.
 * @param url the specified URL
 * @return link like this: <pre>{ "linkAddr": "https://hacpai.com/article/1440573175609", "linkTitle": "????", "linkKeywords": "", "linkHTML": "page HTML", "linkText": "page text", "linkBaiduRefCnt": int } </pre>
 */
public static JSONObject getLink(final String url){
  try {
    return Symphonys.EXECUTOR_SERVICE.submit(new Spider(url)).get();
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Parses URL [" + url + "] failed",e);
    return null;
  }
}
