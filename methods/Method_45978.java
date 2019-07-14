/** 
 * ????????
 * @param context  RpcInvokeContext
 * @param response ??
 */
public static void carryWithResponse(RpcInvokeContext context,SofaResponse response){
  if (context != null) {
    Map<String,String> responseBaggage=context.getAllResponseBaggage();
    if (CommonUtils.isNotEmpty(responseBaggage)) {
      String prefix=RemotingConstants.RPC_RESPONSE_BAGGAGE + ".";
      for (      Map.Entry<String,String> entry : responseBaggage.entrySet()) {
        response.addResponseProp(prefix + entry.getKey(),entry.getValue());
      }
    }
  }
}
