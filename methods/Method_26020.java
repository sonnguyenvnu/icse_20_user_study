static DiagnosticPosition getDiagnosticPosition(int startPosition,Tree tree){
  return new DiagnosticPosition(){
    @Override public JCTree getTree(){
      return (JCTree)tree;
    }
    @Override public int getStartPosition(){
      return startPosition;
    }
    @Override public int getPreferredPosition(){
      return startPosition;
    }
    @Override public int getEndPosition(    EndPosTable endPosTable){
      return startPosition;
    }
  }
;
}
