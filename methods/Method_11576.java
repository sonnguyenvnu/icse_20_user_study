@Override protected List<String> getSourceTexts(){
  List<String> sourceTexts=new ArrayList<String>(getElements().size());
  for (  Element element : getElements()) {
    sourceTexts.add(element.toString());
  }
  return sourceTexts;
}
