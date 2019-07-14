/** 
 * Abbreviates this description to a length that will fit in the database.
 * @param description The description to process.
 * @return The abbreviated version.
 */
public static String abbreviateDescription(String description){
  if (description == null) {
    return null;
  }
  if (description.length() <= 200) {
    return description;
  }
  return description.substring(0,197) + "...";
}
