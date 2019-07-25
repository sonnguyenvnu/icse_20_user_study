public void previous(UnsignedIntegerFourBytes instanceId) throws AVTransportException {
  try {
    findStateMachine(instanceId).previous();
  }
 catch (  TransitionException ex) {
    throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE,ex.getMessage());
  }
}
