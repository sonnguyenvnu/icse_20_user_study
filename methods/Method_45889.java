public static void setReferenceContext(RpcReferenceContext referenceContext){
  RpcInvokeContext.getContext().put(RemotingConstants.INVOKE_CTX_RPC_REF_CTX,referenceContext);
}
