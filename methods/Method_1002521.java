@Override public void flush(ChannelHandlerContext ctx) throws Exception {
  _upgradePromise.addListener(f -> {
    ChannelFuture future=(ChannelFuture)f;
    if (future.isSuccess()) {
      ctx.flush();
    }
  }
);
}
