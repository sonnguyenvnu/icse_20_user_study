private void needAuthenticate(final HttpServletResponse response){
  response.setStatus(401);
  response.setHeader("Cache-Control","no-store");
  response.setDateHeader("Expires",0);
  response.setHeader("WWW-authenticate",AUTH_PREFIX + "Realm=\"Elastic Job Console Auth\"");
}
