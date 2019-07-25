@SuppressWarnings("unchecked") @Override public T build(){
  return AccessController.doPrivileged((PrivilegedAction<T>)() -> {
    InvocationHandler handler;
    if (async) {
      handler=new InvocationHandlerAsync<T>(manager,target,identifier,timeout,exceptionHandler,timeoutHandler);
    }
 else {
      handler=new InvocationHandlerSync<T>(manager,target,identifier,timeout,exceptionHandler,timeoutHandler);
    }
    return (T)Proxy.newProxyInstance(target.getClass().getClassLoader(),interfaceTypes,handler);
  }
);
}
