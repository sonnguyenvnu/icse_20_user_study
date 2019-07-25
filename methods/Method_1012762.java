@Override public void rewind(){
  try {
    api.seek(-60);
  }
 catch (  IOException e) {
    LOGGER.debug("Bad chromecast rwd " + e);
  }
}
