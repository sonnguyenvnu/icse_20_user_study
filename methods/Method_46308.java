@Override public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
  ctx.pipeline().addBefore(ctx.name(),null,new Http2ServerUpgradeHandler.PriorKnowledgeHandler()).addBefore(ctx.name(),"HttpServerCodec",httpServerCodec).replace(this,"HttpServerUpgradeHandler",httpServerUpgradeHandler);
}
