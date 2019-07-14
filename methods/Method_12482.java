public void start(){
  this.subscription=Flux.from(this.publisher).log(this.log.getName(),Level.FINEST).doOnSubscribe(s -> this.log.debug("Subscribed to {} events",this.eventType)).ofType(this.eventType).cast(this.eventType).transform(this::handle).retryWhen(Retry.any().retryMax(Long.MAX_VALUE).doOnRetry(ctx -> this.log.warn("Unexpected error",ctx.exception()))).subscribe();
}
