@Override public void httpResponseReceived(HttpResponseEvent event){
  if (rateLimitStatusListeners.size() != 0) {
    HttpResponse res=event.getResponse();
    TwitterException te=event.getTwitterException();
    RateLimitStatus rateLimitStatus;
    int statusCode;
    if (te != null) {
      rateLimitStatus=te.getRateLimitStatus();
      statusCode=te.getStatusCode();
    }
 else {
      rateLimitStatus=JSONImplFactory.createRateLimitStatusFromResponseHeader(res);
      statusCode=res.getStatusCode();
    }
    if (rateLimitStatus != null) {
      RateLimitStatusEvent statusEvent=new RateLimitStatusEvent(this,rateLimitStatus,event.isAuthenticated());
      if (statusCode == ENHANCE_YOUR_CLAIM || statusCode == SERVICE_UNAVAILABLE || statusCode == TOO_MANY_REQUESTS) {
        for (        RateLimitStatusListener listener : rateLimitStatusListeners) {
          listener.onRateLimitStatus(statusEvent);
          listener.onRateLimitReached(statusEvent);
        }
      }
 else {
        for (        RateLimitStatusListener listener : rateLimitStatusListeners) {
          listener.onRateLimitStatus(statusEvent);
        }
      }
    }
  }
}
