public void play(){
  stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.PLAY).build())).subscribe();
}
