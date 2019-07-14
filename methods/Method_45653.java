/** 
 * ???????
 * @param proxyType    ????
 * @param clazz        ???
 * @param proxyInvoker ?????Invoker
 * @param < T >          ??
 * @return ?????
 * @throws Exception
 */
public static <T>T buildProxy(String proxyType,Class<T> clazz,Invoker proxyInvoker) throws Exception {
  try {
    ExtensionClass<Proxy> ext=ExtensionLoaderFactory.getExtensionLoader(Proxy.class).getExtensionClass(proxyType);
    if (ext == null) {
      throw ExceptionUtils.buildRuntime("consumer.proxy",proxyType,"Unsupported proxy of client!");
    }
    Proxy proxy=ext.getExtInstance();
    return proxy.getProxy(clazz,proxyInvoker);
  }
 catch (  SofaRpcRuntimeException e) {
    throw e;
  }
catch (  Throwable e) {
    throw new SofaRpcRuntimeException(e.getMessage(),e);
  }
}
