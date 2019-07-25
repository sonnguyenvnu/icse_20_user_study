private List<String> sort(List<String> imports,String lineFormat){
  filterMatchingImports(imports);
  mergeNotMatchingItems(false);
  mergeNotMatchingItems(true);
  mergeMatchingItems();
  return getResult(lineFormat);
}
