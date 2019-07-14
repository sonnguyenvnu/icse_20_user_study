/** 
 * Returns the number of nanoseconds of the given duration without throwing or overflowing. <p> Instead of throwing  {@link ArithmeticException}, this method silently saturates to either {@link Long#MAX_VALUE} or {@link Long#MIN_VALUE}. This behavior can be useful when decomposing a duration in order to call a legacy API which requires a  {@code long, TimeUnit} pair.
 */
private static long saturatedToNanos(Duration duration){
  try {
    return duration.toNanos();
  }
 catch (  ArithmeticException tooBig) {
    return duration.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
  }
}
