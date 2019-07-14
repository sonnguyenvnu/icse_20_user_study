/** 
 * ???????(???????????????
 * @param clazz ???
 * @param < T >   ?????
 * @return ????
 * @throws SofaRpcRuntimeException ????????????????????????
 */
public static <T>T newInstance(Class<T> clazz) throws SofaRpcRuntimeException {
  if (clazz.isPrimitive()) {
    return (T)getDefaultPrimitiveValue(clazz);
  }
  T t=getDefaultWrapperValue(clazz);
  if (t != null) {
    return t;
  }
  try {
    if (!(clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers()))) {
      try {
        Constructor<T> constructor=clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor.newInstance();
      }
 catch (      Exception ignore) {
      }
    }
    Constructor<T>[] constructors=(Constructor<T>[])clazz.getDeclaredConstructors();
    if (constructors == null || constructors.length == 0) {
      throw new SofaRpcRuntimeException("The " + clazz.getCanonicalName() + " has no default constructor!");
    }
    Constructor<T> constructor=constructors[0];
    if (constructor.getParameterTypes().length > 0) {
      for (      Constructor<T> c : constructors) {
        if (c.getParameterTypes().length < constructor.getParameterTypes().length) {
          constructor=c;
          if (constructor.getParameterTypes().length == 0) {
            break;
          }
        }
      }
    }
    constructor.setAccessible(true);
    Class<?>[] argTypes=constructor.getParameterTypes();
    Object[] args=new Object[argTypes.length];
    for (int i=0; i < args.length; i++) {
      args[i]=getDefaultPrimitiveValue(argTypes[i]);
    }
    return constructor.newInstance(args);
  }
 catch (  SofaRpcRuntimeException e) {
    throw e;
  }
catch (  Throwable e) {
    throw new SofaRpcRuntimeException(e.getMessage(),e);
  }
}
