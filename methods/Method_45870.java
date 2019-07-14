@Override @SuppressWarnings("unchecked") public <T>T getProxy(Class<T> interfaceClass,Invoker proxyInvoker){
  StringBuilder debug=null;
  if (LOGGER.isDebugEnabled()) {
    debug=new StringBuilder();
  }
  try {
    Class clazz=PROXY_CLASS_MAP.get(interfaceClass);
    if (clazz == null) {
      String interfaceName=ClassTypeUtils.getTypeStr(interfaceClass);
      ClassPool mPool=ClassPool.getDefault();
      mPool.appendClassPath(new LoaderClassPath(ClassLoaderUtils.getClassLoader(JavassistProxy.class)));
      CtClass mCtc=mPool.makeClass(interfaceName + "_proxy_" + counter.getAndIncrement());
      if (interfaceClass.isInterface()) {
        mCtc.addInterface(mPool.get(interfaceName));
      }
 else {
        throw new IllegalArgumentException(interfaceClass.getName() + " is not an interface");
      }
      mCtc.setSuperclass(mPool.get(java.lang.reflect.Proxy.class.getName()));
      CtConstructor constructor=new CtConstructor(null,mCtc);
      constructor.setModifiers(Modifier.PUBLIC);
      constructor.setBody("{super(new " + UselessInvocationHandler.class.getName() + "());}");
      mCtc.addConstructor(constructor);
      List<String> fieldList=new ArrayList<String>();
      List<String> methodList=new ArrayList<String>();
      fieldList.add("public " + Invoker.class.getCanonicalName() + " proxyInvoker = null;");
      createMethod(interfaceClass,fieldList,methodList);
      for (      String fieldStr : fieldList) {
        if (LOGGER.isDebugEnabled()) {
          debug.append(fieldStr).append("\n");
        }
        mCtc.addField(CtField.make(fieldStr,mCtc));
      }
      for (      String methodStr : methodList) {
        if (LOGGER.isDebugEnabled()) {
          debug.append(methodStr).append("\n");
        }
        mCtc.addMethod(CtMethod.make(methodStr,mCtc));
      }
      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("javassist proxy of interface: {} \r\n{}",interfaceClass,debug != null ? debug.toString() : "");
      }
      clazz=mCtc.toClass();
      PROXY_CLASS_MAP.put(interfaceClass,clazz);
    }
    Object instance=clazz.newInstance();
    clazz.getField("proxyInvoker").set(instance,proxyInvoker);
    return (T)instance;
  }
 catch (  Exception e) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("javassist proxy of interface: {} \r\n{}",interfaceClass,debug != null ? debug.toString() : "");
    }
    throw new SofaRpcRuntimeException("",e);
  }
}
