/** 
 * Wrap all signatures of a given method name.
 * @param className
 * @param methodName
 * @throws NotFoundException
 * @throws CannotCompileException
 * @throws IOException
 */
private byte[] wrapClass(String className,boolean wrapConstructors,String... methodNames) throws NotFoundException, IOException, CannotCompileException {
  ClassPool cp=ClassPool.getDefault();
  CtClass ctClazz=cp.get(className);
  if (wrapConstructors) {
    CtConstructor[] constructors=ctClazz.getConstructors();
    for (    CtConstructor constructor : constructors) {
      try {
        constructor.insertBefore("{ com.netflix.hystrix.contrib.networkauditor.HystrixNetworkAuditorAgent.notifyOfNetworkEvent(); }");
      }
 catch (      Exception e) {
        throw new RuntimeException("Failed trying to wrap constructor of class: " + className,e);
      }
    }
  }
  CtMethod[] methods=ctClazz.getDeclaredMethods();
  for (  CtMethod method : methods) {
    try {
      for (      String methodName : methodNames) {
        if (method.getName().equals(methodName)) {
          method.insertBefore("{ com.netflix.hystrix.contrib.networkauditor.HystrixNetworkAuditorAgent.handleNetworkEvent(); }");
        }
      }
    }
 catch (    Exception e) {
      throw new RuntimeException("Failed trying to wrap method [" + method.getName() + "] of class: " + className,e);
    }
  }
  return ctClazz.toBytecode();
}
