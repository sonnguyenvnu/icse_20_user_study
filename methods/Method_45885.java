/** 
 * get the last reference invoke information
 * @param clear true: framework will clear the ThreadLocal when return
 * @return RPC Reference Context, it can be null
 */
public static RpcReferenceContext lastReferenceContext(boolean clear){
  try {
    RpcInvokeContext invokeCtx=RpcInvokeContext.getContext();
    RpcReferenceContext referenceCtx=(RpcReferenceContext)invokeCtx.get(RemotingConstants.INVOKE_CTX_RPC_REF_CTX);
    if (referenceCtx != null) {
      String resultCode=(String)invokeCtx.get(RemotingConstants.INVOKE_CTX_RPC_RESULT_CODE);
      if (resultCode != null) {
        referenceCtx.setResultCode(ResultCodeEnum.getResultCode(resultCode));
      }
    }
    return referenceCtx;
  }
  finally {
    if (clear) {
      clearReferenceContext();
    }
  }
}
