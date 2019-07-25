@Override public void characters(char[] array,int start,int len) throws SAXException {
  globalHandleText(myValues.firstElement(),new String(array,start,len));
  LineToContentMapReader7Handler.ElementHandler current=(myHandlersStack.empty() ? (LineToContentMapReader7Handler.ElementHandler)null : myHandlersStack.peek());
  if (current != null) {
    current.handleText(myValues.peek(),new String(array,start,len));
  }
}
