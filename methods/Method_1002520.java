@Override public void flush(final ChannelHandlerContext ctx) throws Exception {
  _alpnPromise.addListener(f -> {
    ChannelFuture future=(ChannelFuture)f;
    if (future.isSuccess()) {
      ctx.flush();
    }
  }
);
}
