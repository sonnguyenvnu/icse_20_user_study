@Override public Mono<Boolean> transit(StateContext<S,E> context){
  if (guard != null) {
    return guard.apply(context).doOnError(e -> {
      log.warn("Deny guard due to throw as GUARD should not error",e);
    }
).onErrorReturn(false);
  }
  return Mono.just(true);
}
