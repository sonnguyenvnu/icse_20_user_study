/** 
 * Returns matched name or <code>null</code> if name is not matched. <p> Matches if attribute name matches the required field name. If the match is positive, injection is performed on the field. <p> Parameter name matches field name if param name starts with field name and has either '.' or '[' after the field name. <p> Returns real property name, once when name is matched.
 */
public String matchedName(final String value){
  if (!value.startsWith(name)) {
    return null;
  }
  final int requiredLen=name.length();
  if (value.length() >= requiredLen + 1) {
    final char c=value.charAt(requiredLen);
    if ((c != '.') && (c != '[')) {
      return null;
    }
  }
  if (targetName == null) {
    return value;
  }
  return targetName + value.substring(name.length());
}
