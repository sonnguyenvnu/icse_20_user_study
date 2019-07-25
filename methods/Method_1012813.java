/** 
 * Returns a  {@link Rational} whose value is {@code (this * value)}.
 * @param value the value to be multiplied by this {@link Rational}.
 * @return The multiplication result.
 */
@Nullable public Rational multiply(@Nullable BigInteger value){
  if (value == null) {
    return null;
  }
  if (isNaN()) {
    return NaN;
  }
  if (isInfinite()) {
    if (value.signum() == 0) {
      return NaN;
    }
    return numerator.signum() == value.signum() ? POSITIVE_INFINITY : NEGATIVE_INFINITY;
  }
  if (value.signum() == 0) {
    return ZERO;
  }
  if (BigInteger.ONE.equals(value.abs())) {
    return value.signum() < 0 ? negate() : this;
  }
  return valueOf(reducedNumerator.multiply(value),reducedDenominator);
}
