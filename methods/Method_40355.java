private void recordParseErrors(String path,List<RecognitionException> errs){
  if (errs.isEmpty()) {
    return;
  }
  List<Diagnostic> diags=Indexer.idx.getParseErrs(path);
  for (  RecognitionException rx : errs) {
    String msg=rx.line + ":" + rx.charPositionInLine + ":" + rx;
    diags.add(new Diagnostic(path,Diagnostic.Type.ERROR,-1,-1,msg));
  }
}
