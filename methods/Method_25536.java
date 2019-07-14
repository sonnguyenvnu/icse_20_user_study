private static boolean hasAttribute(Symbol sym,Name annotationName){
  return sym.getRawAttributes().stream().anyMatch(a -> a.type.tsym.getQualifiedName().equals(annotationName));
}
