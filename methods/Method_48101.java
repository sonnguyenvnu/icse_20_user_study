/** 
 * Verifies the given (not-null) attribute value is valid. Throws an  {@link IllegalArgumentException} if the value is invalid,otherwise simply returns.
 * @param value to verify
 */
default void verifyAttribute(V value){
  Preconditions.checkNotNull(value,"Provided value cannot be null");
}
