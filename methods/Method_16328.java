@Override public ValueConverter buildValueConverter(DictConfig dictConfig){
  if (CollectionUtils.isEmpty(strategies)) {
    return null;
  }
  return strategies.stream().filter(strategy -> strategy.support(dictConfig.getType())).findFirst().map(strategy -> strategy.buildValueConverter(dictConfig)).orElse(null);
}
