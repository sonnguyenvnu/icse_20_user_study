@Override public void write(String content,ViewType viewType){
  if (viewType == ViewType.xml) {
    content_type="application/xml; charset=UTF-8";
  }
  this.content=content;
}
