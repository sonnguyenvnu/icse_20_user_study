private static String buildTrackName(Format format){
  String trackName;
  if (MimeTypes.isVideo(format.sampleMimeType)) {
    trackName=joinWithSeparator(joinWithSeparator(buildResolutionString(format),buildBitrateString(format)),buildTrackIdString(format));
  }
 else   if (MimeTypes.isAudio(format.sampleMimeType)) {
    trackName=joinWithSeparator(joinWithSeparator(joinWithSeparator(buildLanguageString(format),buildAudioPropertyString(format)),buildBitrateString(format)),buildTrackIdString(format));
  }
 else {
    trackName=joinWithSeparator(joinWithSeparator(buildLanguageString(format),buildBitrateString(format)),buildTrackIdString(format));
  }
  return trackName.length() == 0 ? "unknown" : trackName;
}
