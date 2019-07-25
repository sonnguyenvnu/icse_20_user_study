/** 
 * @param fieldName the field name to match against this  {@link ParseField}
 * @return true if <code>fieldName</code> matches any of the acceptablenames for this  {@link ParseField}.
 */
public boolean match(String fieldName){
  Objects.requireNonNull(fieldName,"fieldName cannot be null");
  if (allReplacedWith == null && fieldName.equals(name)) {
    return true;
  }
  String msg;
  for (  String depName : deprecatedNames) {
    if (fieldName.equals(depName)) {
      msg="Deprecated field [" + fieldName + "] used, expected [" + name + "] instead";
      if (allReplacedWith != null) {
        msg="Deprecated field [" + fieldName + "] used, replaced by [" + allReplacedWith + "]";
      }
      DEPRECATION_LOGGER.deprecated(msg);
      return true;
    }
  }
  return false;
}
