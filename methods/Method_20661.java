/** 
 * Returns a precision based on the RoundingMode.
 * @param amount When this is a whole number, we return 0, otherwise we return 2.
 * @param roundingMode When this is not HALF_UP, we return 0, otherwise we return 2.
 */
private static int getPrecision(final @NonNull Double amount,final @NonNull RoundingMode roundingMode){
  if (roundingMode != RoundingMode.HALF_UP || isWholeNumber(amount)) {
    return 0;
  }
 else {
    return 2;
  }
}
