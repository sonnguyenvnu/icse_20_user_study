/** 
 * Gets a result.
 * @return a result
 */
default boolean getAsBoolean(){
  try {
    return getAsBooleanThrows();
  }
 catch (  Exception e) {
    throw new FunctionalException(e);
  }
}
