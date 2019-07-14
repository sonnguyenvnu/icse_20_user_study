/** 
 * Called before creating a new decoder. Based on number of CPU cores, available memory, and the size of the image file, determines whether another decoder can be created. Subclasses can override and customise this.
 * @param numberOfDecoders the number of decoders that have been created so far
 * @param fileLength the size of the image file in bytes. Creating another decoder will use approximately this much native memory.
 * @return true if another decoder can be created.
 */
@SuppressWarnings("WeakerAccess") protected boolean allowAdditionalDecoder(int numberOfDecoders,long fileLength){
  if (numberOfDecoders >= 4) {
    debug("No additional decoders allowed, reached hard limit (4)");
    return false;
  }
 else   if (numberOfDecoders * fileLength > 20 * 1024 * 1024) {
    debug("No additional encoders allowed, reached hard memory limit (20Mb)");
    return false;
  }
 else   if (numberOfDecoders >= getNumberOfCores()) {
    debug("No additional encoders allowed, limited by CPU cores (" + getNumberOfCores() + ")");
    return false;
  }
 else   if (isLowMemory()) {
    debug("No additional encoders allowed, memory is low");
    return false;
  }
  debug("Additional decoder allowed, current count is " + numberOfDecoders + ", estimated native memory " + ((fileLength * numberOfDecoders) / (1024 * 1024)) + "Mb");
  return true;
}
