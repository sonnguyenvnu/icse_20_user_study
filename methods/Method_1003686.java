@Override public void transmit(HttpResponseStatus responseStatus,ByteBuf body){
  if (body.readableBytes() == 0) {
    body.release();
    transmit(responseStatus,LastHttpContent.EMPTY_LAST_CONTENT,false);
  }
 else {
    transmit(responseStatus,new DefaultLastHttpContent(body),false);
  }
}
