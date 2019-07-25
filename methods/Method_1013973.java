@Modified protected synchronized void modified(Map<String,Object> config){
  if (config.containsKey(PAUSE_BETWEEN_SCANS_IN_SECONDS_ATTRIBUTE)) {
    pauseBetweenScans=Duration.ofSeconds(parseLong(config.get(PAUSE_BETWEEN_SCANS_IN_SECONDS_ATTRIBUTE).toString()));
    if (backgroundScanningJob != null) {
      stopBackgroundScanning();
      startBackgroundScanning();
    }
  }
}
