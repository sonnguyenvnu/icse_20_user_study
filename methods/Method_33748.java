/** 
 * ?????????
 */
public static String formatUrl(String url){
  if (url.startsWith("http")) {
    return url;
  }
 else   if (!url.startsWith("http") && url.contains("http")) {
    url=url.substring(url.indexOf("http"),url.length());
  }
 else   if (url.startsWith("www")) {
    url="http://" + url;
  }
 else   if (!url.startsWith("http") && (url.contains(".me") || url.contains(".com") || url.contains(".cn"))) {
    url="http://www." + url;
  }
 else {
    url="";
  }
  if (url.contains(" ")) {
    int i=url.indexOf(" ");
    url=url.substring(0,i);
  }
  return url;
}
