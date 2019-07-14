/** 
 * Returns license and playback durations remaining in seconds.
 * @param drmSession The drm session to query.
 * @return A {@link Pair} consisting of the remaining license and playback durations in seconds,or null if called before the session has been opened or after it's been released.
 */
public static @Nullable Pair<Long,Long> getLicenseDurationRemainingSec(DrmSession<?> drmSession){
  Map<String,String> keyStatus=drmSession.queryKeyStatus();
  if (keyStatus == null) {
    return null;
  }
  return new Pair<>(getDurationRemainingSec(keyStatus,PROPERTY_LICENSE_DURATION_REMAINING),getDurationRemainingSec(keyStatus,PROPERTY_PLAYBACK_DURATION_REMAINING));
}
