private static boolean checkIfImportsChanged(List<ImportStatement> prevImports,List<ImportStatement> imports){
  if (imports.size() != prevImports.size()) {
    return true;
  }
 else {
    int count=imports.size();
    for (int i=0; i < count; i++) {
      if (!imports.get(i).isSameAs(prevImports.get(i))) {
        return true;
      }
    }
  }
  return false;
}
