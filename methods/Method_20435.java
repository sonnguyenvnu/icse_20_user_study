static boolean isType(Elements elements,Types types,TypeMirror typeMirror,Class<?>... typeNames){
  for (  Class<?> clazz : typeNames) {
    if (isType(elements,types,typeMirror,clazz)) {
      return true;
    }
  }
  return false;
}
