@Override public List<Object> apply(Object o){
  if (o instanceof org.docx4j.wml.FldChar) {
    FldChar fldChar=(FldChar)o;
    if (fldChar.getFldCharType().equals(STFldCharType.BEGIN)) {
      depth++;
      P currentP=pStack.peek();
      if (depth == 1 && !starts.contains(currentP)) {
        starts.add(currentP);
      }
    }
    if (fldChar.getFldCharType().equals(STFldCharType.END)) {
      depth--;
    }
  }
  return null;
}
