/** 
 * Get the optional long value associated with an index. The defaultValue is returned if there is no value for the index, or if the value is not a number and cannot be converted to a number.
 * @param index The index must be between 0 and length() - 1.
 * @param defaultValue     The default value.
 * @return      The value.
 */
public long getLong(int index,long defaultValue){
  try {
    return this.getLong(index);
  }
 catch (  Exception e) {
    return defaultValue;
  }
}
