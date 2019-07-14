private static boolean formatsMatch(Format manifestFormat,Format sampleFormat){
  String manifestFormatMimeType=manifestFormat.sampleMimeType;
  String sampleFormatMimeType=sampleFormat.sampleMimeType;
  int manifestFormatTrackType=MimeTypes.getTrackType(manifestFormatMimeType);
  if (manifestFormatTrackType != C.TRACK_TYPE_TEXT) {
    return manifestFormatTrackType == MimeTypes.getTrackType(sampleFormatMimeType);
  }
 else   if (!Util.areEqual(manifestFormatMimeType,sampleFormatMimeType)) {
    return false;
  }
  if (MimeTypes.APPLICATION_CEA608.equals(manifestFormatMimeType) || MimeTypes.APPLICATION_CEA708.equals(manifestFormatMimeType)) {
    return manifestFormat.accessibilityChannel == sampleFormat.accessibilityChannel;
  }
  return true;
}
