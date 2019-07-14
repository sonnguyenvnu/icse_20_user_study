/** 
 * Converts an actual UTC instant to a local instant with the same local time. This conversion is used before performing a calculation so that the calculation can be done using a simple local zone.
 * @param instantUTC  the UTC instant to convert to local
 * @return the local instant with the same local time
 * @throws ArithmeticException if the result overflows a long
 * @since 1.5
 */
public long convertUTCToLocal(long instantUTC){
  int offset=getOffset(instantUTC);
  long instantLocal=instantUTC + offset;
  if ((instantUTC ^ instantLocal) < 0 && (instantUTC ^ offset) >= 0) {
    throw new ArithmeticException("Adding time zone offset caused overflow");
  }
  return instantLocal;
}
