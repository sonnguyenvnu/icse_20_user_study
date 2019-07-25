@Override public SNode resolve(SNode contextNode,@NotNull String refText){
  SNode result=null;
  for (  SNode n : elements) {
    String name=getName(n);
    if (refText.equals(name)) {
      if (result == null) {
        result=n;
      }
 else {
        return null;
      }
    }
  }
  return result;
}
