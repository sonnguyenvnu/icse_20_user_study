protected static PropertyBuilder.RegexPropertyBuilder prop(String name,String displayName,Map<String,String> descriptorToDisplayNames){
  descriptorToDisplayNames.put(name,displayName);
  return regexProperty(name).desc("Regex which applies to " + displayName + " names");
}
