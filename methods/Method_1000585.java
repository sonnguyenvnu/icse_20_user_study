public Object eject(Object obj){
  try {
    return null == obj ? null : field.get(obj);
  }
 catch (  Exception e) {
    if (log.isInfoEnabled())     log.info("Fail to get value by field",e);
    throw Lang.makeThrow("Fail to get field %s.'%s' because [%s]: %s",field.getDeclaringClass().getName(),field.getName(),Lang.unwrapThrow(e),Lang.unwrapThrow(e).getMessage());
  }
}
