@Deactivate protected void deactivate(){
  try {
    httpService.unregister("/" + PROXY_ALIAS);
  }
 catch (  IllegalArgumentException e) {
  }
}
