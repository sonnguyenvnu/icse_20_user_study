private void fireCompleteEvent(final Request request){
  for (  MocoEventTrigger eventTrigger : eventTriggers) {
    if (eventTrigger.isFor(MocoEvent.COMPLETE)) {
      eventTrigger.fireEvent(request);
    }
  }
}
