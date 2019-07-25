/** 
 * Get or create byte value for the given byte.
 * @param i the byte
 * @return the value
 */
public static ValueByte get(byte i){
  return (ValueByte)Value.cache(new ValueByte(i));
}
