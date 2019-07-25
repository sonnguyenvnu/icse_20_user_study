public void load(Cd cd){
  stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload(Events.LOAD).setHeader(Variables.CD.toString(),cd).build())).subscribe();
}
