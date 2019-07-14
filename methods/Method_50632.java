@Override public String getImage(){
  if (node.getLiteral() != null) {
    return String.valueOf(node.getLiteral());
  }
  return null;
}
