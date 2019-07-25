public BeanBuilder create(BeanCreationDirective directive){
  for (  BeanBuilderCreationStrategy strategy : new CopyOnWriteArrayList<>(pluggedStrategies)) {
    if (strategy.isApplicable(directive)) {
      return strategy.create(directive);
    }
  }
  return null;
}
