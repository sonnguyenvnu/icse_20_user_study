/** 
 * ??????????????????????????????????????
 * @param argTypes ????????
 * @param args     ???????
 * @return ??????? ext instance
 */
public T getExtInstance(Class[] argTypes,Object[] args){
  if (clazz != null) {
    try {
      if (singleton) {
        if (instance == null) {
synchronized (this) {
            if (instance == null) {
              instance=ClassUtils.newInstanceWithArgs(clazz,argTypes,args);
            }
          }
        }
        return instance;
      }
 else {
        return ClassUtils.newInstanceWithArgs(clazz,argTypes,args);
      }
    }
 catch (    Exception e) {
      throw new SofaRpcRuntimeException("create " + clazz.getCanonicalName() + " instance error",e);
    }
  }
  throw new SofaRpcRuntimeException("Class of ExtensionClass is null");
}
