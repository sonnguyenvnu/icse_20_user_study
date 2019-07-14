/** 
 * ( begin auto-generated from unbinary.xml ) Converts a String representation of a binary number to its equivalent integer value. For example, unbinary("00001000") will return 8. ( end auto-generated )
 * @webref data:conversion
 * @param value String to convert to an integer
 * @see PApplet#binary(byte)
 * @see PApplet#hex(int,int)
 * @see PApplet#unhex(String)
 */
static final public int unbinary(String value){
  return Integer.parseInt(value,2);
}
