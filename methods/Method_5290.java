boolean maybeRefreshManifestBeforeLoadingNextChunk(long presentationPositionUs){
  if (!manifest.dynamic) {
    return false;
  }
  if (isWaitingForManifestRefresh) {
    return true;
  }
  boolean manifestRefreshNeeded=false;
  Map.Entry<Long,Long> expiredEntry=ceilingExpiryEntryForPublishTime(manifest.publishTimeMs);
  if (expiredEntry != null) {
    long expiredPointUs=expiredEntry.getValue();
    if (expiredPointUs < presentationPositionUs) {
      expiredManifestPublishTimeUs=expiredEntry.getKey();
      notifyManifestPublishTimeExpired();
      manifestRefreshNeeded=true;
    }
  }
  if (manifestRefreshNeeded) {
    maybeNotifyDashManifestRefreshNeeded();
  }
  return manifestRefreshNeeded;
}
