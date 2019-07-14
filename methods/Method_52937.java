@Override public String getImage(){
  return node instanceof Text ? ((Text)node).getData() : null;
}
