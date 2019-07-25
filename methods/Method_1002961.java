@Override protected void read(){
  lhs=!lhs;
  if (isLhs()) {
    assert children != null;
    if (!children.hasNext()) {
      throw new RuntimeException("Called PairContext.read() too many times!");
    }
    currentChild=children.next();
  }
}
