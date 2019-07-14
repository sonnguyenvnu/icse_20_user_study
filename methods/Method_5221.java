private long getManifestLoadRetryDelayMillis(){
  return Math.min((staleManifestReloadAttempt - 1) * 1000,5000);
}
