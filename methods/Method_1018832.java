/** 
 * ????????MethodCache?????????????????
 * @param name
 * @param methodCache
 * @param paramTypes
 * @param args
 * @return
 * @throws ReflectException
 */
public ReflectionUtils call(String name,Map<String,Vector<Method>> methodCache,Class<?>[] paramTypes,Object... args) throws ReflectException {
  Class<?>[] types=types(args);
  try {
    if (null != methodCache) {
      ReflectionUtils res=callInner(name,methodCache,types,args);
      if (null != res) {
        return res;
      }
    }
    Method method;
    if (paramTypes != null) {
      try {
        method=exactMethod(name,paramTypes);
      }
 catch (      NoSuchMethodException e) {
        method=exactMethod(name,types);
      }
    }
 else {
      method=exactMethod(name,types);
    }
    if (null != methodCache && null != method) {
      Vector<Method> methods=methodCache.get(name);
      if (methods == null) {
        methods=new Vector<Method>(4);
        methodCache.put(name,methods);
      }
      methods.add(method);
    }
    return on(method,object,args);
  }
 catch (  NoSuchMethodException e) {
    try {
      Method method=similarMethod(name,types);
      if (null != methodCache && null != method) {
        Vector<Method> methods=methodCache.get(name);
        if (methods == null) {
          methods=new Vector<Method>(4);
          methodCache.put(name,methods);
        }
        methods.add(method);
      }
      return on(method,object,args);
    }
 catch (    NoSuchMethodException e1) {
      throw new ReflectException(e1);
    }
  }
}
