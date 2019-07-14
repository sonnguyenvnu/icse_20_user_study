/** 
 * Returns a pre-filled builder with the given name and display name (for the description). 
 */
RegexPropertyBuilder defaultProp(String name,String displayName){
  return PropertyFactory.regexProperty(name + "Pattern").desc("Regex which applies to " + displayName.trim() + " names").defaultValue(defaultConvention());
}
