@SuppressWarnings("unchecked") public static <T>T create(Class<T> clazz){
  if (clazz == null) {
    return null;
  }
  Method provider=getProvider(clazz);
  if (provider == NOT_FOUND) {
    Debugger.i("[ProviderPool] provider not found: %s",clazz);
    return null;
  }
 else {
    Debugger.i("[ProviderPool] provider found: %s",provider);
    try {
      return (T)provider.invoke(null);
    }
 catch (    Exception e) {
      Debugger.fatal(e);
    }
  }
  return null;
}
