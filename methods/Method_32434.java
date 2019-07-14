/** 
 * Returns the illegal value assigned to the field as a non-null string.
 * @return the value
 */
public String getIllegalValueAsString(){
  String value=iStringValue;
  if (value == null) {
    value=String.valueOf(iNumberValue);
  }
  return value;
}
