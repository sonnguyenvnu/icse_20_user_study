/** 
 * @return a copy of the provided TreeProps instance; returns null if source is null 
 */
@ThreadSafe(enableChecks=false) public static @Nullable TreeProps copy(@Nullable TreeProps source){
  if (source == null) {
    return null;
  }
  return acquire(source);
}
