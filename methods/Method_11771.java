private static boolean isAnyIncluded(Class<?> klass){
  IncludeCategory annotation=klass.getAnnotation(IncludeCategory.class);
  return annotation == null || annotation.matchAny();
}
