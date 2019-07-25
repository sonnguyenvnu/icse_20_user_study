@Override public void write(String content,ViewType viewType){
  configureMimeType(viewType);
  this.content=content;
}
