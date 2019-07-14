/** 
 * @param url
 * @param charSet
 * @return
 */
public static String urlEncode(String url,String charSet){
  if (url == null || url.trim().length() == 0) {
    return url;
  }
  int splitIndex=url.indexOf("?");
  if (splitIndex <= 0) {
    return url;
  }
  String serviceUrl=url.substring(0,splitIndex);
  String queryString=url.substring(splitIndex + 1,url.length());
  String newQueryString="";
  if (queryString.length() > 0) {
    String[] nameValues=queryString.split("&");
    for (    String nameValue : nameValues) {
      int index=nameValue.indexOf("=");
      String pname=null;
      String pvalue=null;
      if (index < 0) {
        pname=nameValue;
        pvalue="";
      }
 else {
        pname=nameValue.substring(0,index);
        pvalue=nameValue.substring(index + 1,nameValue.length());
        try {
          pvalue=URLEncoder.encode(pvalue,charSet);
        }
 catch (        UnsupportedEncodingException e) {
          throw new IllegalArgumentException("invalid charset : " + charSet);
        }
      }
      newQueryString+=pname + "=" + pvalue + "&";
    }
    newQueryString=newQueryString.substring(0,newQueryString.length() - 1);
  }
  return serviceUrl + "?" + newQueryString;
}
