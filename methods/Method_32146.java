/** 
 * Converts a local instant to an actual UTC instant with the same local time. This conversion is used after performing a calculation where the calculation was done using a simple local zone.
 * @param instantLocal  the local instant to convert to UTC
 * @param strict  whether the conversion should reject non-existent local times
 * @return the UTC instant with the same local time, 
 * @throws ArithmeticException if the result overflows a long
 * @throws IllegalInstantException if the zone has no equivalent local time
 * @since 1.5
 */
public long convertLocalToUTC(long instantLocal,boolean strict){
  int offsetLocal=getOffset(instantLocal);
  int offset=getOffset(instantLocal - offsetLocal);
  if (offsetLocal != offset) {
    if (strict || offsetLocal < 0) {
      long nextLocal=nextTransition(instantLocal - offsetLocal);
      if (nextLocal == (instantLocal - offsetLocal)) {
        nextLocal=Long.MAX_VALUE;
      }
      long nextAdjusted=nextTransition(instantLocal - offset);
      if (nextAdjusted == (instantLocal - offset)) {
        nextAdjusted=Long.MAX_VALUE;
      }
      if (nextLocal != nextAdjusted) {
        if (strict) {
          throw new IllegalInstantException(instantLocal,getID());
        }
 else {
          offset=offsetLocal;
        }
      }
    }
  }
  long instantUTC=instantLocal - offset;
  if ((instantLocal ^ instantUTC) < 0 && (instantLocal ^ offset) < 0) {
    throw new ArithmeticException("Subtracting time zone offset caused overflow");
  }
  return instantUTC;
}
