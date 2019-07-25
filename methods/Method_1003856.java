@Override public <T>Iterable<Supplier<? extends T>> provide(TypeToken<T> type){
  return FluentIterable.from(ImmutableList.copyOf(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory,type.getRawType())).reverse()).transform(beanName -> () -> Types.cast(beanFactory.getBean(beanName)));
}
