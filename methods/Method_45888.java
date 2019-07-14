public static void clearServiceContext(){
  RpcInvokeContext.getContext().remove(RemotingConstants.INVOKE_CTX_RPC_SER_CTX);
}
