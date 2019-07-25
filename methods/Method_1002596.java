private void finish(){
  state=State.ALL_READ;
  request.setBody(content,readCount);
}
