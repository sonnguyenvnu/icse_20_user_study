public void start(){
  subscription=Flux.from(publisher).log(log.getName(),Level.FINEST).doOnSubscribe(s -> log.debug("Subscribed to {} events",eventType)).ofType(eventType).cast(eventType).transform(this::handle).retryWhen(Retry.any().retryMax(Long.MAX_VALUE).doOnRetry(ctx -> log.warn("Unexpected error",ctx.exception()))).subscribe();
}
