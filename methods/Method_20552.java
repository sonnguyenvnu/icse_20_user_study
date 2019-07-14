private void setVisitorCookie(){
  final String deviceId=FirebaseInstanceId.getInstance().getId();
  final String uniqueIdentifier=TextUtils.isEmpty(deviceId) ? UUID.randomUUID().toString() : deviceId;
  final HttpCookie cookie=new HttpCookie("vis",uniqueIdentifier);
  cookie.setMaxAge(DateTime.now().plusYears(100).getMillis());
  cookie.setSecure(true);
  final URI webUri=URI.create(Secrets.WebEndpoint.PRODUCTION);
  final URI apiUri=URI.create(ApiEndpoint.PRODUCTION.url());
  this.cookieManager.getCookieStore().add(webUri,cookie);
  this.cookieManager.getCookieStore().add(apiUri,cookie);
  CookieHandler.setDefault(this.cookieManager);
}
