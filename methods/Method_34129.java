/** 
 * Formats a set of string values into a format appropriate for sending as a single-valued form value.
 * @param value The value of the parameter.
 * @return The value formatted for form submission etc, or null if the input is empty
 */
public static String formatParameterList(Collection<String> value){
  return value == null ? null : StringUtils.collectionToDelimitedString(value," ");
}
