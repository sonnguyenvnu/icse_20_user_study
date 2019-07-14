/** 
 * Returns a number rounded to the specification.
 * @param initialValue Value to convert, local to the project's currency.
 * @param roundingMode When this is DOWN, we get the floor of the initialValue.
 */
private static float getRoundedValue(final double initialValue,final @NonNull RoundingMode roundingMode){
  if (roundingMode == RoundingMode.DOWN) {
    return (float)Math.floor(initialValue);
  }
 else {
    return (float)initialValue;
  }
}
