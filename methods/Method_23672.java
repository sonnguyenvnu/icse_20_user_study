/** 
 * Get an optional double associated with a key, or the defaultValue if there is no such key or if its value is not a number. If the value is a string, an attempt will be made to evaluate it as a number.
 * @param key   A key string.
 * @param defaultValue     The default.
 * @return      An object which is the value.
 */
public double getDouble(String key,double defaultValue){
  try {
    return this.getDouble(key);
  }
 catch (  Exception e) {
    return defaultValue;
  }
}
