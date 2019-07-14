@SuppressWarnings("rawtypes") public void checkListResolve(Collection array){
  if (resolveStatus == NeedToResolve) {
    if (array instanceof List) {
      final int index=array.size() - 1;
      final List list=(List)array;
      ResolveTask task=getLastResolveTask();
      task.fieldDeserializer=new ResolveFieldDeserializer(this,list,index);
      task.ownerContext=context;
      setResolveStatus(DefaultJSONParser.NONE);
    }
 else {
      ResolveTask task=getLastResolveTask();
      task.fieldDeserializer=new ResolveFieldDeserializer(array);
      task.ownerContext=context;
      setResolveStatus(DefaultJSONParser.NONE);
    }
  }
}
