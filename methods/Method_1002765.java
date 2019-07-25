/** 
 * Returns a factory of the specified  {@link BeanFactoryId}.
 */
static Optional<AnnotatedBeanFactory<?>> find(@Nullable BeanFactoryId beanFactoryId){
  if (beanFactoryId == null) {
    return Optional.empty();
  }
  final AnnotatedBeanFactory<?> factory=factories.get(beanFactoryId);
  return factory != null && factory != unsupportedBeanFactory ? Optional.of(factory) : Optional.empty();
}
