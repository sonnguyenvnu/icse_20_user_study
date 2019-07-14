private void onUtcTimestampResolutionError(IOException error){
  Log.e(TAG,"Failed to resolve UtcTiming element.",error);
  processManifest(true);
}
