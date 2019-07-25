/** 
 * Converts the rssi value to null if necessary.
 */
private Integer convert(Integer intValue){
  if (intValue == null || intValue == 65536) {
    return 0;
  }
  return intValue;
}
