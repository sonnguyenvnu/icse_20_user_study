@Override public boolean openUri(URI uri){
  ArrayList<DocumentRange> ranges=new ArrayList<>();
  String fragment=uri.getFragment();
  String query=uri.getQuery();
  Marker.clearMarkAllHighlights(textArea);
  if ((fragment != null) && (declarations.size() == 1)) {
    DeclarationData declaration=declarations.entrySet().iterator().next().getValue();
    if (fragment.equals(declaration.typeName)) {
      ranges.add(new DocumentRange(declaration.startPosition,declaration.endPosition));
    }
  }
  if (query != null) {
    Map<String,String> parameters=parseQuery(query);
    String highlightFlags=parameters.get("highlightFlags");
    String highlightPattern=parameters.get("highlightPattern");
    if ((highlightFlags != null) && (highlightPattern != null)) {
      String regexp=createRegExp(highlightPattern);
      Pattern pattern=Pattern.compile(regexp + ".*");
      boolean t=(highlightFlags.indexOf('t') != -1);
      boolean M=(highlightFlags.indexOf('M') != -1);
      if (highlightFlags.indexOf('d') != -1) {
        for (        Map.Entry<String,DeclarationData> entry : declarations.entrySet()) {
          DeclarationData declaration=entry.getValue();
          if (M) {
            matchAndAddDocumentRange(pattern,declaration.name,declaration.startPosition,declaration.endPosition,ranges);
          }
        }
      }
      if (highlightFlags.indexOf('r') != -1) {
        for (        Map.Entry<Integer,HyperlinkData> entry : hyperlinks.entrySet()) {
          HyperlinkData hyperlink=entry.getValue();
          ReferenceData reference=((HyperlinkReferenceData)hyperlink).reference;
          ModuleInfoReferenceData moduleInfoReferenceData=(ModuleInfoReferenceData)reference;
          if (t && (moduleInfoReferenceData.type == TYPE)) {
            matchAndAddDocumentRange(pattern,getMostInnerTypeName(moduleInfoReferenceData.typeName),hyperlink.startPosition,hyperlink.endPosition,ranges);
          }
          if (M && (moduleInfoReferenceData.type == MODULE)) {
            matchAndAddDocumentRange(pattern,moduleInfoReferenceData.name,hyperlink.startPosition,hyperlink.endPosition,ranges);
          }
        }
      }
    }
  }
  if ((ranges != null) && !ranges.isEmpty()) {
    textArea.setMarkAllHighlightColor(SELECT_HIGHLIGHT_COLOR);
    Marker.markAll(textArea,ranges);
    ranges.sort(null);
    setCaretPositionAndCenter(ranges.get(0));
  }
  return true;
}
