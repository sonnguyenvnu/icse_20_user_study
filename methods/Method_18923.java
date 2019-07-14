private static String getComponentName(String componentClassName,String qualifiedSpecClassName){
  return getComponentTypeName(componentClassName,qualifiedSpecClassName).simpleName();
}
