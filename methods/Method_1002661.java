private void fail(ChannelHandlerContext ctx,Throwable cause){
  state=State.DISCARD;
  final HttpResponseWrapper res=this.res;
  this.res=null;
  if (res != null) {
    res.close(cause);
  }
 else {
    logger.warn("Unexpected exception:",cause);
  }
  ctx.close();
}
