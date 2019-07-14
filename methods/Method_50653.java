@Override public String getImage(){
  if (node.getNames() != null && !node.getNames().isEmpty()) {
    return node.getNames().get(0).getValue();
  }
  return null;
}
