/** 
 * Get a String representation of this variable nodes value.
 * @return a String representing the value.
 */
public String getStringValue(){
  String str;
  if (value != null) {
    if (getType() == TYPE_OBJECT) {
      str="instance of " + type;
    }
 else     if (getType() == TYPE_ARRAY) {
      str=value.toString().substring(0,value.toString().lastIndexOf(" "));
    }
 else     if (getType() == TYPE_STRING) {
      str=((StringReference)value).value();
    }
 else {
      str=value.toString();
    }
  }
 else {
    str="null";
  }
  return str;
}
