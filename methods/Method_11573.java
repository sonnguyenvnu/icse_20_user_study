public List<String> selectDocumentForList(Selector selector){
  if (selector instanceof ElementSelector) {
    ElementSelector elementSelector=(ElementSelector)selector;
    return elementSelector.selectList(getDocument());
  }
 else {
    return selector.selectList(getFirstSourceText());
  }
}
