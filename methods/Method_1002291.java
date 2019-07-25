@SuppressWarnings("unchecked") private static <E>ProxyChannel<E> instantiate(Class<?> proxy,int capacity,WaitStrategy waitStrategy){
  try {
    return (ProxyChannel<E>)proxy.getDeclaredConstructor(int.class,WaitStrategy.class).newInstance(capacity,waitStrategy);
  }
 catch (  Exception e) {
    throw new IllegalStateException(e);
  }
}
