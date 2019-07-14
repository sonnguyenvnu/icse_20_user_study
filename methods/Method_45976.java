/** 
 * ??????????
 * @param context RpcInvokeContext
 * @param request ??
 * @param init    ??????????????
 */
public static void pickupFromRequest(RpcInvokeContext context,SofaRequest request,boolean init){
  if (context == null && !init) {
    return;
  }
  Map<String,String> requestBaggage=(Map<String,String>)request.getRequestProp(RemotingConstants.RPC_REQUEST_BAGGAGE);
  if (CommonUtils.isNotEmpty(requestBaggage)) {
    if (context == null) {
      context=RpcInvokeContext.getContext();
    }
    context.putAllRequestBaggage(requestBaggage);
  }
}
