/** 
 * Gets a result.
 * @return a result
 */
default int getAsInt(){
  try {
    return getAsIntThrows();
  }
 catch (  Exception e) {
    throw new FunctionalException(e);
  }
}
