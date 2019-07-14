public static boolean matchScope(String scope,String type){
  if ((scope == null) || scope.isEmpty())   return true;
  if (scope.charAt(0) == '*')   return type.endsWith(scope.substring(1)) || type.equals(scope.substring(2));
  return type.equals(scope);
}
