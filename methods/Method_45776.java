/** 
 * ?????
 * @param providerInfo ?????
 * @param request      ????
 * @return ????????
 * @throws SofaRpcException ??RPC??
 */
protected SofaResponse filterChain(ProviderInfo providerInfo,SofaRequest request) throws SofaRpcException {
  RpcInternalContext context=RpcInternalContext.getContext();
  context.setProviderInfo(providerInfo);
  return filterChain.invoke(request);
}
