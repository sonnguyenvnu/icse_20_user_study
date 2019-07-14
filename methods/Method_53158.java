private void parseGetParameters(String url,List<HttpParameter> signatureBaseParams){
  int queryStart=url.indexOf("?");
  if (-1 != queryStart) {
    String[] queryStrs=url.substring(queryStart + 1).split("&");
    try {
      for (      String query : queryStrs) {
        String[] split=query.split("=");
        if (split.length == 2) {
          signatureBaseParams.add(new HttpParameter(URLDecoder.decode(split[0],"UTF-8"),URLDecoder.decode(split[1],"UTF-8")));
        }
 else {
          signatureBaseParams.add(new HttpParameter(URLDecoder.decode(split[0],"UTF-8"),""));
        }
      }
    }
 catch (    UnsupportedEncodingException ignore) {
    }
  }
}
