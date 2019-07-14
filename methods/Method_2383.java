/** 
 * ??????????code
 * @param request
 */
private boolean validateClient(ServletRequest request,ServletResponse response){
  Subject subject=getSubject(request,response);
  Session session=subject.getSession();
  String sessionId=session.getId().toString();
  int timeOut=(int)session.getTimeout() / 1000;
  String cacheClientSession=RedisUtil.get(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + session.getId());
  if (StringUtils.isNotBlank(cacheClientSession)) {
    RedisUtil.set(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + sessionId,cacheClientSession,timeOut);
    Jedis jedis=RedisUtil.getJedis();
    jedis.expire(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + cacheClientSession,timeOut);
    jedis.close();
    if (null != request.getParameter("code")) {
      String backUrl=RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
      HttpServletResponse httpServletResponse=WebUtils.toHttp(response);
      try {
        httpServletResponse.sendRedirect(backUrl.toString());
      }
 catch (      IOException e) {
        LOGGER.error("??????????code???????",e);
      }
    }
 else {
      return true;
    }
  }
  String code=request.getParameter("upms_code");
  if (StringUtils.isNotBlank(code)) {
    try {
      StringBuffer ssoServerUrl=new StringBuffer(PropertiesFileUtil.getInstance("zheng-upms-client").get("zheng.upms.sso.server.url"));
      HttpClient httpclient=new DefaultHttpClient();
      HttpPost httpPost=new HttpPost(ssoServerUrl.toString() + "/sso/code");
      List<NameValuePair> nameValuePairs=new ArrayList<>();
      nameValuePairs.add(new BasicNameValuePair("code",code));
      httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      HttpResponse httpResponse=httpclient.execute(httpPost);
      if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        HttpEntity httpEntity=httpResponse.getEntity();
        JSONObject result=JSONObject.parseObject(EntityUtils.toString(httpEntity));
        if (1 == result.getIntValue("code") && result.getString("data").equals(code)) {
          RedisUtil.set(ZHENG_UPMS_CLIENT_SESSION_ID + "_" + sessionId,code,timeOut);
          RedisUtil.sadd(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code,sessionId,timeOut);
          LOGGER.debug("??code={}???????????{}?",code,RedisUtil.getJedis().scard(ZHENG_UPMS_CLIENT_SESSION_IDS + "_" + code));
          String backUrl=RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
          try {
            String username=request.getParameter("upms_username");
            subject.login(new UsernamePasswordToken(username,""));
            HttpServletResponse httpServletResponse=WebUtils.toHttp(response);
            httpServletResponse.sendRedirect(backUrl.toString());
            return true;
          }
 catch (          IOException e) {
            LOGGER.error("???code???code???????",e);
          }
        }
 else {
          LOGGER.warn(result.getString("data"));
        }
      }
    }
 catch (    IOException e) {
      LOGGER.error("??token???",e);
    }
  }
  return false;
}
