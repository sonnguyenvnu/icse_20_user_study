private static boolean shouldKeepInitialDiscontinuity(long startUs,TrackSelection[] selections){
  if (startUs != 0) {
    for (    TrackSelection trackSelection : selections) {
      if (trackSelection != null) {
        Format selectedFormat=trackSelection.getSelectedFormat();
        if (!MimeTypes.isAudio(selectedFormat.sampleMimeType)) {
          return true;
        }
      }
    }
  }
  return false;
}
