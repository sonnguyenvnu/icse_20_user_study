/** 
 * Parses a string parameter value into a set of strings.
 * @param values The values of the set.
 * @return The set.
 */
public static Set<String> parseParameterList(String values){
  Set<String> result=new TreeSet<String>();
  if (values != null && values.trim().length() > 0) {
    String[] tokens=values.split("[\\s+]");
    result.addAll(Arrays.asList(tokens));
  }
  return result;
}
