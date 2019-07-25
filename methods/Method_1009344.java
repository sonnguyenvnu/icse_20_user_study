public ConditionExpression<S,D> using(Converter<?,?> converter){
  saveLastMapping();
  if (converter == null)   errors.errorNullArgument("converter");
  Assert.state(options.converter == null,"using() can only be called once per mapping.");
  options.converter=converter;
  return this;
}
