@Override public void characters(char[] array,int start,int len) throws SAXException {
  ModelReader8Handler.ElementHandler current=(myHandlersStack.empty() ? (ModelReader8Handler.ElementHandler)null : myHandlersStack.peek());
  if (current != null) {
    current.handleText(myValues.peek(),new String(array,start,len));
  }
}
