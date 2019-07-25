private SourceUnit find(Element element,List<SourceUnit> list){
  for (  SourceUnit sourceUnit : list) {
    if (sourceUnit.contains(element)) {
      return sourceUnit;
    }
  }
  SourceUnit result=new SourceUnit(element);
  list.add(result);
  return result;
}
