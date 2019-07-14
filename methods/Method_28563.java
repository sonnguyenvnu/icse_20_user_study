public void scrollToLine(@NonNull String url){
  String[] lineNo=getLineNo(url);
  if (lineNo != null && lineNo.length > 1) {
    loadUrl("javascript:scrollToLineNumber('" + lineNo[0] + "', '" + lineNo[1] + "')");
  }
 else   if (lineNo != null) {
    loadUrl("javascript:scrollToLineNumber('" + lineNo[0] + "', '0')");
  }
}
