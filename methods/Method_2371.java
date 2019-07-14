/** 
 * ????
 * @param url
 * @param publishDto
 * @return
 */
public BaseResult publish(String url,PublishDto publishDto){
  HttpPost httpPost=null;
  try {
    HttpClient httpClient=new DefaultHttpClient();
    httpPost=new HttpPost(url);
    httpPost.setHeader("Content-type","application/json; charset=utf-8");
    HttpEntity httpEntity=new StringEntity(JSONObject.toJSONString(publishDto),"utf-8");
    httpPost.setEntity(httpEntity);
    HttpResponse httpResponse=httpClient.execute(httpPost);
    int statusCode=httpResponse.getStatusLine().getStatusCode();
    if (statusCode == HttpStatus.SC_OK) {
      HttpEntity resEntity=httpResponse.getEntity();
      if (resEntity != null) {
        String result=EntityUtils.toString(resEntity,Charset.forName("utf-8"));
        return JSONObject.parseObject(result,BaseResult.class);
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    if (httpPost != null) {
      httpPost.releaseConnection();
    }
  }
  return new BaseResult(-1,"error","publish error");
}
