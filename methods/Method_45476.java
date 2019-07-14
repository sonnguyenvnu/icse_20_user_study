/** 
 * ????????
 * @param routerName ????
 * @since 5.2.0
 */
protected void recordRouterWay(String routerName){
  if (RpcInternalContext.isAttachmentEnable()) {
    RpcInternalContext context=RpcInternalContext.getContext();
    String record=(String)context.getAttachment(RpcConstants.INTERNAL_KEY_ROUTER_RECORD);
    record=record == null ? routerName : record + ">" + routerName;
    context.setAttachment(RpcConstants.INTERNAL_KEY_ROUTER_RECORD,record);
  }
}
