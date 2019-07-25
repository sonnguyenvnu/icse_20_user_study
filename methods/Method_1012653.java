@Override public DLNAImage copy(){
  try {
    return new DLNAImage(bytes,imageInfo,profile,true);
  }
 catch (  DLNAProfileException e) {
    LOGGER.error("Impossible situation in DLNAImage.copy(): {}",e.getMessage());
    LOGGER.trace("",e);
    return null;
  }
}
