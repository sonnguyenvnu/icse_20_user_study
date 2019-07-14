@SuppressWarnings("rawtypes") public void checkMapResolve(Map object,Object fieldName){
  if (resolveStatus == NeedToResolve) {
    ResolveFieldDeserializer fieldResolver=new ResolveFieldDeserializer(object,fieldName);
    ResolveTask task=getLastResolveTask();
    task.fieldDeserializer=fieldResolver;
    task.ownerContext=context;
    setResolveStatus(DefaultJSONParser.NONE);
  }
}
