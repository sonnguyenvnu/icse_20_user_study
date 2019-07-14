/** 
 * The argument is interpreted as the display name, and is converted to camel case to get the property name. 
 */
RegexPropertyBuilder defaultProp(String displayName){
  return defaultProp(StringUtil.toCamelCase(displayName,true),displayName);
}
