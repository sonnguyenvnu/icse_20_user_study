@Override public void update(Function<Number,Number> operation){
  value=operation.apply(value);
}
