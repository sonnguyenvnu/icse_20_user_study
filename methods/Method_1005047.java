@Override public Operation execute() throws InstantiationException, IllegalAccessException {
  return new OperationChain(examplesFactory.generateExample(GetAllElements.class),new Limit.Builder().resultLimit(1).build());
}
