synchronized public void receive(UnsignedIntegerFourBytes sequence,Collection<StateVariableValue> newValues){
  if (this.currentSequence != null) {
    if (this.currentSequence.getValue().equals(this.currentSequence.getBits().getMaxValue()) && sequence.getValue() == 1) {
      System.err.println("TODO: HANDLE ROLLOVER");
      return;
    }
    if (this.currentSequence.getValue() >= sequence.getValue()) {
      return;
    }
    int difference;
    long expectedValue=currentSequence.getValue() + 1;
    if ((difference=(int)(sequence.getValue() - expectedValue)) != 0) {
      eventsMissed(difference);
    }
  }
  this.currentSequence=sequence;
  for (  StateVariableValue newValue : newValues) {
    currentValues.put(newValue.getStateVariable().getName(),newValue);
  }
  eventReceived();
}
