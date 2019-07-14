public String getRange(JCCompilationUnit unit){
  try {
    CharSequence sequence=unit.getSourceFile().getCharContent(true);
    return sequence.subSequence(location.getStartPosition(),location.getEndPosition(unit.endPositions)).toString();
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
