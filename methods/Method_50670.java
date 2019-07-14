@Override public String getImage(){
  if (node.getIdentifier() != null) {
    return node.getIdentifier().getValue();
  }
  return null;
}
