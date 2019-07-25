@Override public void characters(char[] array,int start,int len) throws SAXException {
  ModelReader9Handler.ElementHandler current=(myHandlersStack.empty() ? (ModelReader9Handler.ElementHandler)null : myHandlersStack.peek());
  if (current != null) {
    current.handleText(myValues.peek(),new String(array,start,len));
  }
}
