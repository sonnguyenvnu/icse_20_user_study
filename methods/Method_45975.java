/** 
 * ????????
 * @param context RpcInvokeContext
 * @param request ??
 */
public static void carryWithRequest(RpcInvokeContext context,SofaRequest request){
  if (context != null) {
    Map<String,String> requestBaggage=context.getAllRequestBaggage();
    if (CommonUtils.isNotEmpty(requestBaggage)) {
      request.addRequestProp(RemotingConstants.RPC_REQUEST_BAGGAGE,requestBaggage);
    }
  }
}
