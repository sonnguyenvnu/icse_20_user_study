/** 
 * ??????????????
 * @param property    ??
 * @param newValueStr ?????
 * @param overwrite   ???? true?????false???
 * @return ????? boolean
 */
public boolean updateAttribute(String property,String newValueStr,boolean overwrite){
  try {
    boolean changed=false;
    if (property.charAt(0) == RpcConstants.HIDE_KEY_PREFIX) {
      String methodAndP=property.substring(1);
      int index=methodAndP.indexOf(RpcConstants.HIDE_KEY_PREFIX);
      if (index <= 0) {
        throw ExceptionUtils.buildRuntime(property,newValueStr,"Unknown update attribute key!");
      }
      String methodName=methodAndP.substring(0,index);
      String methodProperty=methodAndP.substring(index + 1);
      MethodConfig methodConfig=getMethodConfig(methodName);
      Method getMethod=ReflectUtils.getPropertyGetterMethod(MethodConfig.class,methodProperty);
      Class propertyClazz=getMethod.getReturnType();
      Object oldValue=null;
      Object newValue=CompatibleTypeUtils.convert(newValueStr,propertyClazz);
      if (methodConfig == null) {
        methodConfig=new MethodConfig();
        methodConfig.setName(methodName);
        if (this.methods == null) {
          this.methods=new ConcurrentHashMap<String,MethodConfig>();
        }
        this.methods.put(methodName,methodConfig);
        changed=true;
      }
 else {
        oldValue=BeanUtils.getProperty(methodConfig,methodProperty,propertyClazz);
        if (oldValue == null) {
          if (newValueStr != null) {
            changed=true;
          }
        }
 else {
          changed=!oldValue.equals(newValue);
        }
      }
      if (changed && overwrite) {
        BeanUtils.setProperty(methodConfig,methodProperty,propertyClazz,newValue);
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Property \"" + methodName + "." + methodProperty + "\" changed from {} to {}",oldValue,newValueStr);
        }
      }
    }
 else {
      Method getMethod=ReflectUtils.getPropertyGetterMethod(getClass(),property);
      Class propertyClazz=getMethod.getReturnType();
      Object oldValue=BeanUtils.getProperty(this,property,propertyClazz);
      Object newValue=CompatibleTypeUtils.convert(newValueStr,propertyClazz);
      if (oldValue == null) {
        if (newValueStr != null) {
          changed=true;
        }
      }
 else {
        changed=!oldValue.equals(newValue);
      }
      if (changed && overwrite) {
        BeanUtils.setProperty(this,property,propertyClazz,newValue);
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("Property \"" + property + "\" changed from {} to {}",oldValue,newValueStr);
        }
      }
    }
    return changed;
  }
 catch (  Exception e) {
    throw new SofaRpcRuntimeException("Exception when update attribute, The key is " + property + " and value is " + newValueStr,e);
  }
}
