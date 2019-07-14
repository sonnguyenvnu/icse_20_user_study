@Override public String getImage(){
  if (node.getLocalInfo() != null) {
    return node.getLocalInfo().getName();
  }
  return null;
}
