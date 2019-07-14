public static void matchQueryAndAddDocumentRange(Map<String,String> parameters,HashMap<String,DeclarationData> declarations,TreeMap<Integer,HyperlinkData> hyperlinks,ArrayList<StringData> strings,List<DocumentRange> ranges){
  String highlightFlags=parameters.get("highlightFlags");
  String highlightPattern=parameters.get("highlightPattern");
  if ((highlightFlags != null) && (highlightPattern != null)) {
    String highlightScope=parameters.get("highlightScope");
    String regexp=createRegExp(highlightPattern);
    Pattern pattern=Pattern.compile(regexp + ".*");
    if (highlightFlags.indexOf('s') != -1) {
      Pattern patternForString=Pattern.compile(regexp);
      for (      StringData data : strings) {
        if (matchScope(highlightScope,data.owner)) {
          Matcher matcher=patternForString.matcher(data.text);
          int offset=data.startPosition;
          while (matcher.find()) {
            ranges.add(new DocumentRange(offset + matcher.start(),offset + matcher.end()));
          }
        }
      }
    }
    boolean t=(highlightFlags.indexOf('t') != -1);
    boolean f=(highlightFlags.indexOf('f') != -1);
    boolean m=(highlightFlags.indexOf('m') != -1);
    boolean c=(highlightFlags.indexOf('c') != -1);
    if (highlightFlags.indexOf('d') != -1) {
      for (      Map.Entry<String,DeclarationData> entry : declarations.entrySet()) {
        DeclarationData declaration=entry.getValue();
        if (matchScope(highlightScope,declaration.typeName)) {
          if ((t && declaration.isAType()) || (c && declaration.isAConstructor())) {
            matchAndAddDocumentRange(pattern,getMostInnerTypeName(declaration.typeName),declaration.startPosition,declaration.endPosition,ranges);
          }
          if ((f && declaration.isAField()) || (m && declaration.isAMethod())) {
            matchAndAddDocumentRange(pattern,declaration.name,declaration.startPosition,declaration.endPosition,ranges);
          }
        }
      }
    }
    if (highlightFlags.indexOf('r') != -1) {
      for (      Map.Entry<Integer,HyperlinkData> entry : hyperlinks.entrySet()) {
        HyperlinkData hyperlink=entry.getValue();
        ReferenceData reference=((HyperlinkReferenceData)hyperlink).reference;
        if (matchScope(highlightScope,reference.owner)) {
          if ((t && reference.isAType()) || (c && reference.isAConstructor())) {
            matchAndAddDocumentRange(pattern,getMostInnerTypeName(reference.typeName),hyperlink.startPosition,hyperlink.endPosition,ranges);
          }
          if ((f && reference.isAField()) || (m && reference.isAMethod())) {
            matchAndAddDocumentRange(pattern,reference.name,hyperlink.startPosition,hyperlink.endPosition,ranges);
          }
        }
      }
    }
  }
}
