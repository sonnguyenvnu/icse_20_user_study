/** 
 * @param selector selector
 * @return result
 */
public String selectDocument(Selector selector){
  if (selector instanceof ElementSelector) {
    ElementSelector elementSelector=(ElementSelector)selector;
    return elementSelector.select(getDocument());
  }
 else {
    return selector.select(getFirstSourceText());
  }
}
