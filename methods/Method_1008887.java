private DocumentFragment result(Document document){
  if (outputType == RunFontActionType.DISCOVERY) {
    return null;
  }
  DocumentFragment docfrag=document.createDocumentFragment();
  docfrag.appendChild(document.getDocumentElement());
  return docfrag;
}
