@Override public void verify(StateMachineModel<S,E> model){
  if (model.getTransitionsData().getTransitions().isEmpty()) {
    MalformedConfigurationException exception=new MalformedConfigurationException("Must have at least one transition");
    throw exception;
  }
  Iterator<Node<StateData<S,E>>> iterator=buildStateDataIterator(model.getStatesData());
  while (iterator.hasNext()) {
    Node<StateData<S,E>> node=iterator.next();
    if (node.getData() == null) {
      boolean initialStateFound=false;
      for (      Node<StateData<S,E>> n : node.getChildren()) {
        StateData<S,E> data=n.getData();
        if (data.isInitial()) {
          initialStateFound=true;
        }
      }
      if (!initialStateFound) {
        MalformedConfigurationException exception=new MalformedConfigurationException("Initial state not set");
        for (        Node<StateData<S,E>> c : node.getChildren()) {
          exception.addTrace(c.getData().toString());
        }
        throw exception;
      }
    }
  }
}
