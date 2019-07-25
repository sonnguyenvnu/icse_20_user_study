/** 
 * Performs the initial partitioning of the partitioner's document.  It seems to be called the first time a document is loaded during a Toolbox session.  It seems to get called 2-4 times, each for a different TLAFastPartitioner object.  Fortunately, the documentChanged method is called only once for each change. <p> May be extended by subclasses. </p>
 */
protected void initialize(){
  fIsInitialized=true;
  clearPositionCache();
  fScanner.setRange(fDocument,0,fDocument.getLength());
  try {
    IToken token=fScanner.nextToken();
    while (!token.isEOF()) {
      String contentType=getTokenContentType(token);
      if (isSupportedContentType(contentType)) {
        TypedPosition p=new TypedPosition(fScanner.getTokenOffset(),fScanner.getTokenLength(),contentType);
        fDocument.addPosition(fPositionCategory,p);
      }
      token=fScanner.nextToken();
    }
  }
 catch (  BadLocationException x) {
  }
catch (  BadPositionCategoryException x) {
  }
}
