/** 
 * Updates the  {@link DashManifest} that this handler works on.
 * @param newManifest The updated manifest.
 */
public void updateManifest(DashManifest newManifest){
  isWaitingForManifestRefresh=false;
  expiredManifestPublishTimeUs=C.TIME_UNSET;
  this.manifest=newManifest;
  removePreviouslyExpiredManifestPublishTimeValues();
}
