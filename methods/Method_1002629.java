/** 
 * Parse constant value string and set representation based on type
 * @param value             expressed as a String
 * @param primitiveType     that this is supposed to be
 * @param characterEncoding of the constant value.
 * @return a new {@link PrimitiveValue} for the value.
 * @throws IllegalArgumentException if parsing malformed type
 */
public static PrimitiveValue parse(final String value,final PrimitiveType primitiveType,final String characterEncoding){
  if (PrimitiveType.CHAR != primitiveType) {
    throw new IllegalArgumentException("primitiveType must be char: " + primitiveType);
  }
  if (value.length() > 1) {
    throw new IllegalArgumentException("Constant char value malformed: " + value);
  }
  return new PrimitiveValue(value.getBytes(forName(characterEncoding))[0],characterEncoding);
}
