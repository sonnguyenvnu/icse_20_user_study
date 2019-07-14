private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder){
  if (mediaSourceHolder.isRemoved && mediaSourceHolder.hasStartedPreparing && mediaSourceHolder.activeMediaPeriods.isEmpty()) {
    releaseChildSource(mediaSourceHolder);
  }
}
