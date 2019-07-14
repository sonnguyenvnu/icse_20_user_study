private @Nullable Map.Entry<Long,Long> ceilingExpiryEntryForPublishTime(long publishTimeMs){
  return manifestPublishTimeToExpiryTimeUs.ceilingEntry(publishTimeMs);
}
