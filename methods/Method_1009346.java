public ConditionExpression<S,D> with(Provider<?> provider){
  saveLastMapping();
  if (provider == null)   errors.errorNullArgument("provider");
  Assert.state(options.provider == null,"withProvider() can only be called once per mapping.");
  options.provider=provider;
  return this;
}
