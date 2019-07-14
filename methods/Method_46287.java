protected void pickupBaggage(SofaResponse response){
  if (RpcInvokeContext.isBaggageEnable()) {
    RpcInvokeContext invokeCtx=null;
    if (context != null) {
      invokeCtx=(RpcInvokeContext)context.getAttachment(RpcConstants.HIDDEN_KEY_INVOKE_CONTEXT);
    }
    if (invokeCtx == null) {
      invokeCtx=RpcInvokeContext.getContext();
    }
 else {
      RpcInvokeContext.setContext(invokeCtx);
    }
    BaggageResolver.pickupFromResponse(invokeCtx,response);
  }
}
