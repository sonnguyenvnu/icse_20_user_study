private static String getClassName(ClassSymbol s){
  if (s.isAnonymous()) {
    return s.getSuperclass().tsym.getSimpleName().toString();
  }
 else {
    return s.getSimpleName().toString();
  }
}
