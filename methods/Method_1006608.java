/** 
 * Oneway invocation.
 * @param conn
 * @param request
 * @throws InterruptedException
 */
protected void oneway(final Connection conn,final RemotingCommand request){
  try {
    conn.getChannel().writeAndFlush(request).addListener(new ChannelFutureListener(){
      @Override public void operationComplete(      ChannelFuture f) throws Exception {
        if (!f.isSuccess()) {
          logger.error("Invoke send failed. The address is {}",RemotingUtil.parseRemoteAddress(conn.getChannel()),f.cause());
        }
      }
    }
);
  }
 catch (  Exception e) {
    if (null == conn) {
      logger.error("Conn is null");
    }
 else {
      logger.error("Exception caught when sending invocation. The address is {}",RemotingUtil.parseRemoteAddress(conn.getChannel()),e);
    }
  }
}
