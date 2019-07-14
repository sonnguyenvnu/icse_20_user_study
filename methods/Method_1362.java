/** 
 * Convert an int [0-255] to a hexadecimal string representation.
 * @param value int value.
 */
public static String byte2Hex(int value){
  if (value > 255 || value < 0) {
    throw new IllegalArgumentException("The int converting to hex should be in range 0~255");
  }
  return String.valueOf(FIRST_CHAR[value]) + String.valueOf(SECOND_CHAR[value]);
}
