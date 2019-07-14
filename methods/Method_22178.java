private void authenticateSuccess(final HttpServletResponse response,final boolean isGuest){
  response.setStatus(200);
  response.setHeader("Pragma","No-cache");
  response.setHeader("Cache-Control","no-store");
  response.setDateHeader("Expires",0);
  response.setHeader("identify",isGuest ? GUEST_IDENTIFY : ROOT_IDENTIFY);
}
