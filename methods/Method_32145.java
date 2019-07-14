/** 
 * Converts a local instant to an actual UTC instant with the same local time attempting to use the same offset as the original. <p> This conversion is used after performing a calculation where the calculation was done using a simple local zone. Whenever possible, the same offset as the original offset will be used. This is most significant during a daylight savings overlap.
 * @param instantLocal  the local instant to convert to UTC
 * @param strict  whether the conversion should reject non-existent local times
 * @param originalInstantUTC  the original instant that the calculation is based on
 * @return the UTC instant with the same local time, 
 * @throws ArithmeticException if the result overflows a long
 * @throws IllegalArgumentException if the zone has no equivalent local time
 * @since 2.0
 */
public long convertLocalToUTC(long instantLocal,boolean strict,long originalInstantUTC){
  int offsetOriginal=getOffset(originalInstantUTC);
  long instantUTC=instantLocal - offsetOriginal;
  int offsetLocalFromOriginal=getOffset(instantUTC);
  if (offsetLocalFromOriginal == offsetOriginal) {
    return instantUTC;
  }
  return convertLocalToUTC(instantLocal,strict);
}
