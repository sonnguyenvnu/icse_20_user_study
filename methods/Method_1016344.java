/** 
 * Encode the value using DoubleMetaphone.
 * @param value String to encode
 * @return An encoded String
 */
@Override public String encode(String value){
  return doubleMetaphone(value);
}
