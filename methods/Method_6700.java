private void checkConversionCanceled(){
  boolean cancelConversion;
synchronized (videoConvertSync) {
    cancelConversion=cancelCurrentVideoConversion;
  }
  if (cancelConversion) {
    throw new RuntimeException("canceled conversion");
  }
}
