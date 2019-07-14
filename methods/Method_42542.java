/** 
 * ??????????HTTP?POST?????????????????
 * @param sParaTemp ????
 * @return ???????
 * @throws Exception
 */
public HttpResponse buildRequest(Map<String,String> sParaTemp) throws Exception {
  Map<String,String> sPara=AlipaySubmit.buildRequestPara(sParaTemp);
  HttpProtocolHandler httpProtocolHandler=HttpProtocolHandler.getInstance();
  HttpRequest request=new HttpRequest(HttpResultType.BYTES);
  request.setCharset(charset);
  request.setParameters(AlipaySubmit.generatNameValuePair(sPara));
  request.setUrl(url + "_input_charset=" + charset);
  HttpResponse response=httpProtocolHandler.execute(request,"","");
  if (response == null) {
    return null;
  }
  return response;
}
