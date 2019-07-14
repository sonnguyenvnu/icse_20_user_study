protected void openHyperlink(int x,int y,HyperlinkData hyperlinkData){
  LogHyperlinkData logHyperlinkData=(LogHyperlinkData)hyperlinkData;
  if (logHyperlinkData.enabled) {
    try {
      Point location=textArea.getLocationOnScreen();
      int offset=textArea.viewToModel(new Point(x - location.x,y - location.y));
      api.addURI(new URI(uri.getScheme(),uri.getAuthority(),uri.getPath(),"position=" + offset,null));
      String text=getText();
      String typeAndMethodNames=text.substring(hyperlinkData.startPosition,hyperlinkData.endPosition);
      int lastDotIndex=typeAndMethodNames.lastIndexOf('.');
      String methodName=typeAndMethodNames.substring(lastDotIndex + 1);
      String internalTypeName=typeAndMethodNames.substring(0,lastDotIndex).replace('.','/');
      List<Container.Entry> entries=IndexesUtil.findInternalTypeName(collectionOfFutureIndexes,internalTypeName);
      int leftParenthesisIndex=hyperlinkData.endPosition + 1;
      int rightParenthesisIndex=text.indexOf(')',leftParenthesisIndex);
      String lineNumberOrNativeMethodFlag=text.substring(leftParenthesisIndex,rightParenthesisIndex);
      if (lineNumberOrNativeMethodFlag.equals("Native Method")) {
        lastDotIndex=internalTypeName.lastIndexOf('/');
        String shortTypeName=internalTypeName.substring(lastDotIndex + 1);
        api.openURI(x,y,entries,null,shortTypeName + '-' + methodName + "-(*)?");
      }
 else {
        int colonIndex=lineNumberOrNativeMethodFlag.indexOf(':');
        String lineNumber=lineNumberOrNativeMethodFlag.substring(colonIndex + 1);
        api.openURI(x,y,entries,"lineNumber=" + lineNumber,null);
      }
    }
 catch (    Exception e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
}
