/** 
 * Acquires filter from Petite container.
 */
@Override protected <R extends ActionFilter>R createWrapper(final Class<R> wrapperClass){
  return petiteContainer.createBean(wrapperClass);
}
