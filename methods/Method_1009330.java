public ConfigurableConditionExpression<S,D> using(Converter<?,?> converter){
  notNull(converter,"converter");
  options.converter=converter;
  return this;
}
