protected boolean isAdminURL(URL url){
  return Constants.ADMIN_PROTOCOL.equals(url.getProtocol());
}
