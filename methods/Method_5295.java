private void handleManifestExpiredMessage(long eventTimeUs,long manifestPublishTimeMsInEmsg){
  Long previousExpiryTimeUs=manifestPublishTimeToExpiryTimeUs.get(manifestPublishTimeMsInEmsg);
  if (previousExpiryTimeUs == null) {
    manifestPublishTimeToExpiryTimeUs.put(manifestPublishTimeMsInEmsg,eventTimeUs);
  }
 else {
    if (previousExpiryTimeUs > eventTimeUs) {
      manifestPublishTimeToExpiryTimeUs.put(manifestPublishTimeMsInEmsg,eventTimeUs);
    }
  }
}
