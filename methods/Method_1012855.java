/** 
 * Unqualify a string qualified by a separator character. For example, "this:name:is:qualified" returns "qualified" if using a ':' separator.
 * @param qualifiedName the qualified name
 * @param separator     the separator
 */
public static String unqualify(String qualifiedName,char separator){
  return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
}
