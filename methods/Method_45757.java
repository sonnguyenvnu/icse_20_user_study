protected static boolean isPublicInstanceField(Field field){
  return Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers()) && !field.isSynthetic();
}
