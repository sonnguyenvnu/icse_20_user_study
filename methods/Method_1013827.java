private ChannelHandlerContext ctx(){
  ChannelHandlerContext ctx=this.ctx;
  if (ctx == null) {
    throw new IllegalStateException("not added to a pipeline");
  }
  return ctx;
}
