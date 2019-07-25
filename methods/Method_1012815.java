/** 
 * Returns a  {@link Rational} whose value is {@code (this * value)}.
 * @param value the value to be multiplied by this {@link Rational}.
 * @return The multiplication result.
 */
@Nonnull public Rational multiply(double value){
  if (isNaN() || Double.isNaN(value)) {
    return NaN;
  }
  if (isInfinite() || Double.isInfinite(value)) {
    if (signum() == 0 || value == 0f) {
      return NaN;
    }
    if (value > 0) {
      return signum() > 0 ? POSITIVE_INFINITY : NEGATIVE_INFINITY;
    }
    return signum() < 0 ? POSITIVE_INFINITY : NEGATIVE_INFINITY;
  }
  if (value == 0f) {
    return ZERO;
  }
  return multiply(valueOf(value));
}
