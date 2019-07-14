/** 
 * Get the optional boolean value associated with an index. It returns the defaultValue if there is no value at that index or if it is not a Boolean or the String "true" or "false" (case insensitive).
 * @param index The index must be between 0 and length() - 1.
 * @param defaultValue     A boolean default.
 * @return      The truth.
 */
public boolean getBoolean(int index,boolean defaultValue){
  try {
    return getBoolean(index);
  }
 catch (  Exception e) {
    return defaultValue;
  }
}
