/** 
 * Returns a boolean that determines if a Double is a whole number.
 * @param value a Double to be verified if it is a whole number.
 */
private static Boolean isWholeNumber(final @NonNull Double value){
  return value == Math.round(value);
}
