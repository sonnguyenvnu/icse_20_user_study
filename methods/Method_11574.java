/** 
 * Only document can be select See: https://github.com/code4craft/webmagic/issues/113
 * @param elementIterator elementIterator
 * @return element element
 */
private Element checkElementAndConvert(ListIterator<Element> elementIterator){
  Element element=elementIterator.next();
  if (!(element instanceof Document)) {
    Document root=new Document(element.ownerDocument().baseUri());
    Element clone=element.clone();
    root.appendChild(clone);
    elementIterator.set(root);
    return root;
  }
  return element;
}
