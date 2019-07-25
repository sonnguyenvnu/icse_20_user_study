/** 
 * Get????????
 * @param url ??URL???https://api.weixin.qq.com/cgi-bin/token
 * @param parameters url??????:new URLParameter("appid",xxxxx)
 * @return ????
 * @throws WeixinException
 */
public WeixinResponse get(String url,URLParameter... parameters) throws WeixinException {
  StringBuilder buf=new StringBuilder(url);
  if (parameters != null && parameters.length > 0) {
    buf.append("&").append(FormUrlEntity.formatParameters(Arrays.asList(parameters)));
  }
  HttpRequest request=new HttpRequest(HttpMethod.GET,buf.toString());
  return doRequest(request);
}
