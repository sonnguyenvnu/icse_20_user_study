/** 
 * Calls  {@link #validate(long)} for each struct contained in the specified struct array.
 * @param array the struct array to validate
 * @param count the number of structs in {@code array}
 */
public static void validate(long array,int count){
  for (int i=0; i < count; i++) {
    validate(array + Integer.toUnsignedLong(i) * SIZEOF);
  }
}
