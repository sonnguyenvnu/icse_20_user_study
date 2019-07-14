private boolean tryToMatch(final Request request,final Document document) throws SAXException {
  Document resourceDocument=getResourceDocument(request,this.resource);
  return document.isEqualNode(resourceDocument);
}
