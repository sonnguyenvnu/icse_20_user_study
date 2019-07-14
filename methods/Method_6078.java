@Override public int supportsFormat(Format format){
  return MimeTypes.APPLICATION_CAMERA_MOTION.equals(format.sampleMimeType) ? FORMAT_HANDLED : FORMAT_UNSUPPORTED_TYPE;
}
