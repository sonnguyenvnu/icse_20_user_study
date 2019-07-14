public static boolean isSubtypeable(Class<?> parameter,Class<?> argument){
  return isSubtypeable(JavaTypeDefinition.forClass(parameter),JavaTypeDefinition.forClass(argument));
}
