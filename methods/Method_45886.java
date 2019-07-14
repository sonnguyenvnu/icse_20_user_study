/** 
 * get current service context
 * @param clear true: framework will clear the ThreadLocal when return
 * @return RPC Service Context, it can be null
 */
public static RpcServiceContext currentServiceContext(boolean clear){
  try {
    RpcInvokeContext invokeCtx=RpcInvokeContext.getContext();
    return (RpcServiceContext)invokeCtx.get(RemotingConstants.INVOKE_CTX_RPC_SER_CTX);
  }
  finally {
    if (clear) {
      clearServiceContext();
    }
  }
}
