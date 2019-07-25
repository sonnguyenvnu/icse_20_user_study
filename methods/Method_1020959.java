@Override public void write(int httpStatus,String content,ViewType viewType){
  if (viewType == ViewType.xml) {
    content_type="application/xml; charset=UTF-8";
  }
  this.content=content;
}
