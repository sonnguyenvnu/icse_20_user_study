@Nullable @Override public SNode resolve(SNode contextNode,@NotNull String refText){
  SNode result=null;
  for (  SNode node : nodes) {
    if (refText.equals(getReferenceText(node))) {
      if (result == null) {
        result=node;
      }
 else {
        return null;
      }
    }
  }
  return result;
}
