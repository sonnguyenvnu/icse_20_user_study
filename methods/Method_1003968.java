@Override protected void read(){
  lhs=!lhs;
  if (isLhs()) {
    if (!children.hasNext()) {
      throw new RuntimeException("Called PairContext.read() too many times!");
    }
    currentChild=children.next();
  }
}
