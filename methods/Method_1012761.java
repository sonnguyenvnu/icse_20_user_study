public void forward(){
  try {
    api.seek(60);
  }
 catch (  IOException e) {
    LOGGER.debug("Bad chromecast fwd " + e);
  }
}
