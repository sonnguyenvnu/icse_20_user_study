/** 
 * ( begin auto-generated from unhex.xml ) Converts a String representation of a hexadecimal number to its equivalent integer value. ( end auto-generated )
 * @webref data:conversion
 * @param value String to convert to an integer
 * @see PApplet#hex(int,int)
 * @see PApplet#binary(byte)
 * @see PApplet#unbinary(String)
 */
static final public int unhex(String value){
  return (int)(Long.parseLong(value,16));
}
