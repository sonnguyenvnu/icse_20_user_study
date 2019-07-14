public boolean needParse(String value,DictParserFormat format){
  return value.contains(format.getSplitter()) || value.contains(format.getChildSplitter()) || value.contains(format.getChildStartChar());
}
