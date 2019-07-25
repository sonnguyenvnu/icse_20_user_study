/** 
 * Returns a  {@link Rational} whose value is {@code (this / value)}.
 * @param value the value by which this {@link Rational} is to be divided.
 * @return The division result.
 */
@Nullable public Rational divide(@Nullable BigInteger value){
  if (value == null) {
    return null;
  }
  if (isNaN()) {
    return NaN;
  }
  if (value.signum() == 0) {
    if (signum() == 0) {
      return NaN;
    }
    return signum() > 0 ? POSITIVE_INFINITY : NEGATIVE_INFINITY;
  }
  if (signum() == 0 || isInfinite() || BigInteger.ONE.equals(value.abs())) {
    return value.signum() < 0 ? negate() : this;
  }
  if (value.signum() < 0) {
    return valueOf(reducedNumerator.negate(),reducedDenominator.multiply(value.negate()));
  }
  return valueOf(reducedNumerator,reducedDenominator.multiply(value));
}
