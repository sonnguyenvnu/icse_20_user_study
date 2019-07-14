/** 
 * Abbreviates this script to a length that will fit in the database.
 * @param script The script to process.
 * @return The abbreviated version.
 */
public static String abbreviateScript(String script){
  if (script == null) {
    return null;
  }
  if (script.length() <= 1000) {
    return script;
  }
  return "..." + script.substring(3,1000);
}
