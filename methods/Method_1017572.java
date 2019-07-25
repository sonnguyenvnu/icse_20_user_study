public void pause(){
  stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.PAUSE).build())).subscribe();
}
