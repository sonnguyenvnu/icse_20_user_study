public void next(UnsignedIntegerFourBytes instanceId) throws AVTransportException {
  try {
    findStateMachine(instanceId).next();
  }
 catch (  TransitionException ex) {
    throw new AVTransportException(AVTransportErrorCode.TRANSITION_NOT_AVAILABLE,ex.getMessage());
  }
}
