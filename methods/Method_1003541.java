private boolean equals(List<TypeMirror> mirrors1,List<TypeMirror> mirrors2){
  if (mirrors1 == null) {
    return (mirrors2 == null);
  }
 else   if (mirrors2 == null || mirrors1.size() != mirrors2.size()) {
    return false;
  }
  for (int i=0; i < mirrors1.size(); i++) {
    if (!equals(mirrors1.get(i),mirrors2.get(i))) {
      return false;
    }
  }
  return true;
}
