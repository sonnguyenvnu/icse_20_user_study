@SuppressWarnings("unchecked") public static <T>DoubleCheckLazyProvider<T> create(Provider<T> provider){
  if (provider instanceof DoubleCheckLazyProvider) {
    return (DoubleCheckLazyProvider<T>)provider;
  }
 else {
    return new DoubleCheckLazyProvider<>(provider);
  }
}
