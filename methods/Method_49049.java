@Override public <C extends GraphComputer>C compute(Class<C> graphComputerClass) throws IllegalArgumentException {
  if (!graphComputerClass.equals(FulgoraGraphComputer.class)) {
    throw Graph.Exceptions.graphDoesNotSupportProvidedGraphComputer(graphComputerClass);
  }
 else {
    return (C)compute();
  }
}
