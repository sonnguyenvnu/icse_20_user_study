/** 
 * Acquires interceptor from Petite container.
 */
@Override protected <R extends ActionInterceptor>R createWrapper(final Class<R> wrapperClass){
  return petiteContainer.createBean(wrapperClass);
}
