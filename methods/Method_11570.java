@Override public List<String> selectList(Element doc){
  List<String> strings=new ArrayList<String>();
  List<Element> elements=selectElements(doc);
  if (CollectionUtils.isNotEmpty(elements)) {
    for (    Element element : elements) {
      String value=getValue(element);
      if (value != null) {
        strings.add(value);
      }
    }
  }
  return strings;
}
