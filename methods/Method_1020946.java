@Override public void write(int httpStatus,String content,ViewType viewType){
  configureMimeType(viewType);
  this.content=content;
  this.status=httpStatus;
}
