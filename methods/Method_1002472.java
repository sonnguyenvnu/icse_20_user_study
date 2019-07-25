/** 
 * Capitalize the input name.
 * @param name the string whose first character will be converted to uppercase
 * @return the converted name
 */
public static String capitalize(String name){
  if (name == null || name.isEmpty()) {
    return name;
  }
 else {
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }
}
