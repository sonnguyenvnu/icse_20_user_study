public void forward(){
  stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.FORWARD).setHeader(Headers.TRACKSHIFT.toString(),1).build())).subscribe();
}
