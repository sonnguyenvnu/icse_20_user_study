private HttpResponse post(String url,JSONObject json) throws TwitterException {
  ensureAuthorizationEnabled();
  if (!conf.isMBeanEnabled()) {
    return http.post(url,new HttpParameter[]{new HttpParameter(json)},auth,this);
  }
 else {
    HttpResponse response=null;
    long start=System.currentTimeMillis();
    try {
      response=http.post(url,new HttpParameter[]{new HttpParameter(json)},auth,this);
    }
  finally {
      long elapsedTime=System.currentTimeMillis() - start;
      TwitterAPIMonitor.getInstance().methodCalled(url,elapsedTime,isOk(response));
    }
    return response;
  }
}
