void onDashManifestPublishTimeExpired(long expiredManifestPublishTimeUs){
  if (this.expiredManifestPublishTimeUs == C.TIME_UNSET || this.expiredManifestPublishTimeUs < expiredManifestPublishTimeUs) {
    this.expiredManifestPublishTimeUs=expiredManifestPublishTimeUs;
  }
}
