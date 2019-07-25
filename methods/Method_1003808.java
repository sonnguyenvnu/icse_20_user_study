@Override public Operation wrap(Operation operation){
  return Operation.of(() -> get().wrap(operation).then());
}
