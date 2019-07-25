@Override public void update(Function<Number,Number> operation){
  this.value=operation.apply(value).doubleValue();
}
