private void putToContextIfNotNull(InvokeContext invokeContext,String oldKey,RpcInternalContext context,String key){
  Object value=invokeContext.get(oldKey);
  if (value != null) {
    context.setAttachment(key,value);
  }
}
