@Override public void prepare(){
  userAgent=String.format(USER_AGENT,deviceName,buildVersion);
  auth="AidLogin " + androidId + ":" + securityToken;
  gmsVersion=Constants.MAX_REFERENCE_VERSION;
}
