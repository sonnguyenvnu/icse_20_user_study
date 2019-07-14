/** 
 * Returns a new CachedDateTimeZone unless given zone is already cached.
 */
public static CachedDateTimeZone forZone(DateTimeZone zone){
  if (zone instanceof CachedDateTimeZone) {
    return (CachedDateTimeZone)zone;
  }
  return new CachedDateTimeZone(zone);
}
