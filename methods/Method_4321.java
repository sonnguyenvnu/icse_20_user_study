/** 
 * Returns whether  {@code drmSessionManager} supports the specified {@code drmInitData}, or true if  {@code drmInitData} is null.
 * @param drmSessionManager The drm session manager.
 * @param drmInitData {@link DrmInitData} of the format to check for support.
 * @return Whether {@code drmSessionManager} supports the specified {@code drmInitData}, or true if  {@code drmInitData} is null.
 */
protected static boolean supportsFormatDrm(@Nullable DrmSessionManager<?> drmSessionManager,@Nullable DrmInitData drmInitData){
  if (drmInitData == null) {
    return true;
  }
 else   if (drmSessionManager == null) {
    return false;
  }
  return drmSessionManager.canAcquireSession(drmInitData);
}
