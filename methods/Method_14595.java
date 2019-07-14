@Override public String idFromValueAndType(Object instance,Class<?> type){
  String id=OperationRegistry.s_opClassToName.get(type);
  if (id != null) {
    return id;
  }
 else {
    return ((AbstractOperation)instance).getOperationId();
  }
}
