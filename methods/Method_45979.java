/** 
 * ??????????
 * @param context  RpcInvokeContext
 * @param response ??
 * @param init     ??????????????
 */
public static void pickupFromResponse(RpcInvokeContext context,SofaResponse response,boolean init){
  if (context == null && !init) {
    return;
  }
  Map<String,String> responseBaggage=response.getResponseProps();
  if (CommonUtils.isNotEmpty(responseBaggage)) {
    String prefix=RemotingConstants.RPC_RESPONSE_BAGGAGE + ".";
    for (    Map.Entry<String,String> entry : responseBaggage.entrySet()) {
      if (entry.getKey().startsWith(prefix)) {
        if (context == null) {
          context=RpcInvokeContext.getContext();
        }
        context.putResponseBaggage(entry.getKey().substring(prefix.length()),entry.getValue());
      }
    }
  }
}
