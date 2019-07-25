private void done(ChannelHandlerContext ctx){
  try {
    callback.done(buffer.toString(),null);
  }
  finally {
    ctx.channel().close();
  }
}
