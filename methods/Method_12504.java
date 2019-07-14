public void start(){
  Scheduler scheduler=Schedulers.newSingle(this.name + "-check");
  this.subscription=Flux.interval(this.interval).doOnSubscribe(s -> log.debug("Scheduled {}-check every {}",this.name,this.interval)).log(log.getName(),Level.FINEST).subscribeOn(scheduler).concatMap(i -> this.checkAllInstances()).retryWhen(Retry.any().retryMax(Long.MAX_VALUE).doOnRetry(ctx -> log.warn("Unexpected error in {}-check",this.name,ctx.exception()))).doFinally(s -> scheduler.dispose()).subscribe();
}
