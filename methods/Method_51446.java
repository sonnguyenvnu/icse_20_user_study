/** 
 * Checks if the values are valid.
 * @param defaultValue The default value
 * @param delim        The delimiter
 * @throws IllegalArgumentException if one value contains the delimiter
 */
private static void checkDefaults(List<String> defaultValue,char delim){
  if (defaultValue == null) {
    return;
  }
  for (  String aDefaultValue : defaultValue) {
    if (aDefaultValue.indexOf(delim) >= 0) {
      throw new IllegalArgumentException("Cannot include the delimiter in the set of defaults");
    }
  }
}
