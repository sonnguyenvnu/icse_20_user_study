@Override public DLNAThumbnail copy(){
  try {
    return new DLNAThumbnail(bytes,imageInfo,profile,true);
  }
 catch (  DLNAProfileException e) {
    LOGGER.error("Impossible situation in DLNAImage.copy(): {}",e.getMessage());
    LOGGER.trace("",e);
    return null;
  }
}
