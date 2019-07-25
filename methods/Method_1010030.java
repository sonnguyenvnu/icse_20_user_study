/** 
 * Parses entry, searches for file references and stores them in  {@link #myReferences}.
 */
@Override protected void reparse(){
  ProgressManager.checkCanceled();
  String str=StringUtil.trimEnd(getPathString(),getSeparatorString());
  final List<FileReference> referencesList=ContainerUtil.newArrayList();
  String separatorString=getSeparatorString();
  int sepLen=separatorString.length();
  int currentSlash=-sepLen;
  int startInElement=getStartInElement();
  while (currentSlash + sepLen < str.length() && Character.isWhitespace(str.charAt(currentSlash + sepLen))) {
    currentSlash++;
  }
  if (currentSlash + sepLen + sepLen < str.length() && str.substring(currentSlash + sepLen,currentSlash + sepLen + sepLen).equals(separatorString)) {
    currentSlash+=sepLen;
  }
  int index=0;
  if (str.equals(separatorString)) {
    final FileReference fileReference=createFileReference(new TextRange(startInElement,startInElement + sepLen),index++,separatorString);
    referencesList.add(fileReference);
  }
  while (true) {
    ProgressManager.checkCanceled();
    final int nextSlash=str.indexOf(separatorString,currentSlash + sepLen);
    final String subReferenceText=nextSlash > 0 ? str.substring(0,nextSlash) : str;
    TextRange range=new TextRange(startInElement + currentSlash + sepLen,startInElement + (nextSlash > 0 ? nextSlash : str.length()));
    final FileReference ref=createFileReference(range,index++,subReferenceText);
    referencesList.add(ref);
    if ((currentSlash=nextSlash) < 0) {
      break;
    }
  }
  myReferences=referencesList.toArray(new FileReference[0]);
}
