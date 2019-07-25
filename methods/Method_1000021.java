public void activate(ChannelHandlerContext ctx){
  this.ctx=ctx;
  sendMsgFlag=true;
  sendTask=sendTimer.scheduleAtFixedRate(() -> {
    try {
      if (sendMsgFlag) {
        send();
      }
    }
 catch (    Exception e) {
      logger.error("Unhandled exception",e);
    }
  }
,10,10,TimeUnit.MILLISECONDS);
  sendMsgThread=new Thread(() -> {
    while (sendMsgFlag) {
      try {
        if (msgQueue.isEmpty()) {
          Thread.sleep(10);
          continue;
        }
        Message msg=msgQueue.take();
        ctx.writeAndFlush(msg.getSendData()).addListener((ChannelFutureListener)future -> {
          if (!future.isSuccess() && !channel.isDisconnect()) {
            logger.error("Fail send to {}, {}",ctx.channel().remoteAddress(),msg);
          }
        }
);
      }
 catch (      Exception e) {
        logger.error("Fail send to {}, error info: {}",ctx.channel().remoteAddress(),e.getMessage());
      }
    }
  }
);
  sendMsgThread.setName("sendMsgThread-" + ctx.channel().remoteAddress());
  sendMsgThread.start();
}
