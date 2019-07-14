static boolean isType(Elements elements,Types types,TypeMirror typeMirror,Class<?> clazz){
  TypeMirror classType=KotlinUtilsKt.getTypeMirror(clazz,elements);
  return types.isSameType(typeMirror,classType);
}
