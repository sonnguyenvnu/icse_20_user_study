public ConditionExpression<S,D> when(Condition<?,?> condition){
  saveLastMapping();
  if (condition == null)   errors.errorNullArgument("condition");
  Assert.state(options.condition == null,"when() can only be called once per mapping.");
  options.condition=condition;
  return this;
}
