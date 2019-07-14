/** 
 * Applies this function to the given argument.
 * @param value the function argument
 * @return the function result
 */
default long applyAsLong(int value){
  try {
    return applyAsLongThrows(value);
  }
 catch (  Exception e) {
    throw new FunctionalException(e);
  }
}
