/** 
 * get??
 * @param requestUrl ????
 * @return ??????
 */
public static String requestGet(final String requestUrl){
  logger.info("????--GET?????{}",requestUrl);
  HttpClient httpClient=HttpClientUtil.getHttpClient();
  HttpGet httpGet=new HttpGet(requestUrl);
  try {
    HttpResponse httpResponse=httpClient.execute(httpGet);
    HttpEntity httpEntity=httpResponse.getEntity();
    String result=EntityUtils.toString(httpEntity,"UTF-8");
    logger.info("????--GET???????{}",result);
    return result;
  }
 catch (  IOException e) {
    logger.error("????--GET?????{}",e);
    return null;
  }
}
