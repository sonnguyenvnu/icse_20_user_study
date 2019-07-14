/** 
 * ?????
 * @param property ??
 * @return oldValue ???
 */
public String queryAttribute(String property){
  try {
    Object oldValue=null;
    if (property.charAt(0) == RpcConstants.HIDE_KEY_PREFIX) {
      String methodAndP=property.substring(1);
      int index=methodAndP.indexOf(RpcConstants.HIDE_KEY_PREFIX);
      if (index <= 0) {
        throw ExceptionUtils.buildRuntime(property,"","Unknown query attribute key!");
      }
      String methodName=methodAndP.substring(0,index);
      String methodProperty=methodAndP.substring(index + 1);
      MethodConfig methodConfig=getMethodConfig(methodName);
      if (methodConfig != null) {
        Method getMethod=ReflectUtils.getPropertyGetterMethod(MethodConfig.class,methodProperty);
        Class propertyClazz=getMethod.getReturnType();
        oldValue=BeanUtils.getProperty(methodConfig,methodProperty,propertyClazz);
      }
    }
 else {
      Method getMethod=ReflectUtils.getPropertyGetterMethod(getClass(),property);
      Class propertyClazz=getMethod.getReturnType();
      oldValue=BeanUtils.getProperty(this,property,propertyClazz);
    }
    return oldValue == null ? null : oldValue.toString();
  }
 catch (  Exception e) {
    throw new SofaRpcRuntimeException("Exception when query attribute, The key is " + property,e);
  }
}
