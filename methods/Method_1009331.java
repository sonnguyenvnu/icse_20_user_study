public ConfigurableConditionExpression<S,D> when(Condition<?,?> condition){
  notNull(condition,"condition");
  options.condition=condition;
  return this;
}
