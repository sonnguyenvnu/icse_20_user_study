public static RpcCmdContext getInstance(){
  if (context == null) {
synchronized (RpcCmdContext.class) {
      if (context == null) {
        context=new RpcCmdContext();
      }
    }
  }
  return context;
}
