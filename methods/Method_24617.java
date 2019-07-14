/** 
 * Get a String description of this  {@link VariableNode}. Contains the type, name and value.
 * @return the description
 */
public String getDescription(){
  String str="";
  if (type != null) {
    str+=type + " ";
  }
  str+=name;
  str+=" = " + getStringValue();
  return str;
}
