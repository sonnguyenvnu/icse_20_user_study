private void removePreviouslyExpiredManifestPublishTimeValues(){
  for (Iterator<Map.Entry<Long,Long>> it=manifestPublishTimeToExpiryTimeUs.entrySet().iterator(); it.hasNext(); ) {
    Map.Entry<Long,Long> entry=it.next();
    long expiredManifestPublishTime=entry.getKey();
    if (expiredManifestPublishTime < manifest.publishTimeMs) {
      it.remove();
    }
  }
}
