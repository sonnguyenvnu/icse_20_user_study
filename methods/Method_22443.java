protected boolean hasImport(String importName){
  if (imports != null && importName != null) {
    for (    String c : imports) {
      if (importName.equals(c)) {
        return true;
      }
    }
  }
  return false;
}
