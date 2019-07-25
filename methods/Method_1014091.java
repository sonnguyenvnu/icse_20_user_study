public void start(){
  try {
    ks.spot(this,source.getInputStream(format),locale,this.keyword);
  }
 catch (  KSException e) {
    logger.error("Encountered error calling spot: {}",e.getMessage());
  }
catch (  AudioException e) {
    logger.error("Error creating the audio stream",e);
  }
}
