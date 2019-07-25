public void inject(Object obj,Object value){
  Object v=null;
  try {
    Type realType=ReflectTool.getInheritGenericType(obj.getClass(),type);
    Class<?> realValueType=Lang.getTypeClass(realType);
    if (isMapCollection && value != null && value instanceof String) {
      v=Json.fromJson(realType,value.toString());
    }
 else {
      v=Castors.me().castTo(value,realValueType);
    }
    if (NutConf.USE_FASTCLASS) {
      if (fm == null)       fm=FastClassFactory.get(setter);
      fm.invoke(obj,v);
    }
 else {
      setter.invoke(obj,v);
    }
  }
 catch (  Exception _e) {
    Throwable e=_e;
    if (e instanceof InvocationTargetException)     e=((InvocationTargetException)e).getTargetException();
    if (log.isInfoEnabled())     log.info("Fail to value by setter",e);
    throw Lang.wrapThrow(e,"Fail to set '%s'[ %s ] by setter %s.'%s()' because [%s]: %s",value,v == null ? value : v,setter.getDeclaringClass().getName(),setter.getName(),Lang.unwrapThrow(e),Lang.unwrapThrow(e).getMessage());
  }
}
