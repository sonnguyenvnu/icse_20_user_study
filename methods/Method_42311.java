private static String buildBitrateString(Format format){
  return format.bitrate == Format.NO_VALUE ? "" : String.format(Locale.US,"%.2fMbit",format.bitrate / 1000000f);
}
