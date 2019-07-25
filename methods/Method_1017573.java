public void eject(){
  stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.EJECT).build())).subscribe();
}
