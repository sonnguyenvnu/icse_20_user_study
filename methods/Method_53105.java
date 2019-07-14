private void init(HttpResponse res){
  this.rateLimitStatus=RateLimitStatusJSONImpl.createFromResponseHeader(res);
  accessLevel=ParseUtil.toAccessLevel(res);
}
