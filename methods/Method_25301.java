@Override public String toString(JCCompilationUnit compilationUnit){
  StringBuilder result=new StringBuilder("replace ");
  for (  Replacement replacement : getReplacements(compilationUnit.endPositions)) {
    result.append(String.format("position %d:%d with \"%s\" ",replacement.startPosition(),replacement.endPosition(),replacement.replaceWith()));
  }
  return result.toString();
}
