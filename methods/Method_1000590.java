public void inject(Object obj,Object value){
  Object v=null;
  try {
    Class<?> ft=ReflectTool.getGenericFieldType(obj.getClass(),field);
    v=Castors.me().castTo(value,ft);
    field.set(obj,v);
  }
 catch (  Exception e) {
    String msg=String.format("Fail to set field[%s#%s] using value[%s]",field.getDeclaringClass().getName(),field.getName(),value);
    throw new RuntimeException(msg,e);
  }
}
