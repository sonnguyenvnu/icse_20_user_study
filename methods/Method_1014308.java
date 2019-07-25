/** 
 * Reads the event from the Homematic gateway and handles the method call.
 */
@Override public void run(){
  try {
    boolean isMaxAliveReached;
    do {
      BinRpcMessage message=new BinRpcMessage(socket.getInputStream(),true,config.getEncoding());
      logger.trace("Event BinRpcMessage: {}",message);
      byte[] returnValue=rpcResponseHandler.handleMethodCall(message.getMethodName(),message.getResponseData());
      if (returnValue != null) {
        socket.getOutputStream().write(returnValue);
      }
      isMaxAliveReached=System.currentTimeMillis() - created > (config.getSocketMaxAlive() * 1000);
    }
 while (!isMaxAliveReached);
  }
 catch (  EOFException eof) {
  }
catch (  Exception e) {
    logger.warn("{}",e.getMessage(),e);
  }
 finally {
    try {
      socket.close();
    }
 catch (    IOException ioe) {
    }
  }
}
