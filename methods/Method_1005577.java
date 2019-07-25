/** 
 * ??????  {@link ServiceLoader}
 */
@SuppressWarnings("unchecked") public static <T>ServiceLoader<T> load(Class<T> interfaceClass){
  sInitHelper.ensureInit();
  if (interfaceClass == null) {
    Debugger.fatal(new NullPointerException("ServiceLoader.load?class??????"));
    return EmptyServiceLoader.INSTANCE;
  }
  ServiceLoader service=SERVICES.get(interfaceClass);
  if (service == null) {
synchronized (SERVICES) {
      service=SERVICES.get(interfaceClass);
      if (service == null) {
        service=new ServiceLoader(interfaceClass);
        SERVICES.put(interfaceClass,service);
      }
    }
  }
  return service;
}
