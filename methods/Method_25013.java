public boolean useGzipWhenAccepted(){
  if (gzipUsage == GzipUsage.DEFAULT)   return getMimeType() != null && (getMimeType().toLowerCase().contains("text/") || getMimeType().toLowerCase().contains("/json"));
 else   return gzipUsage == GzipUsage.ALWAYS;
}
