public static void clearReferenceContext(){
  RpcInvokeContext.getContext().remove(RemotingConstants.INVOKE_CTX_RPC_REF_CTX);
}
