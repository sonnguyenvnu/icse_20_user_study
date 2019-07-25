@Override public Mono<Void> entry(StateContext<S,E> context){
  return Mono.defer(() -> {
    if (submachine != null) {
      Disposable disposable=Mono.just(submachine).flatMap(submachine -> completionStateListenerSink(submachine)).then(Mono.fromRunnable(() -> notifyStateOnComplete(context))).subscribe();
      disposables.add(disposable);
    }
 else     if (!regions.isEmpty()) {
      Flux.fromIterable(regions).flatMap(region -> completionStateListenerSink(region)).then(handleStateDoOnComplete(context)).then(Mono.fromRunnable(() -> notifyStateOnComplete(context))).subscribe();
    }
    stateListener.onEntry(context);
    armTriggers();
    return Mono.empty();
  }
).then(scheduleStateActions(context));
}
