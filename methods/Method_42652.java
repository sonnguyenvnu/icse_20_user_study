/** 
 * ???HttpURLConnection??????
 * @param url ????
 * @param params ????
 * @param method ????
 * @param charSet
 * @return ??????null, ?????????
 */
public static String httpRequest(String url,Map<String,String> params,String method,String charSet,Map<String,String> headers){
  SimpleHttpParam param=new SimpleHttpParam(url);
  if (null != param) {
    param.setParameters(params);
  }
  if (null != headers) {
    param.setHeaders(headers);
  }
  param.setCharSet(charSet);
  param.setMethod(method);
  SimpleHttpResult result=httpRequest(param);
  if (result == null || !result.isSuccess()) {
    return null;
  }
 else {
    return result.getContent();
  }
}
