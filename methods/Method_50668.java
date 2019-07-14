public String getType(){
  if (node.getLocalInfo() != null) {
    return node.getLocalInfo().getType().getApexName();
  }
  return null;
}
