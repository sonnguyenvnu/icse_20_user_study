@Override public Mono<ClientResponse> execute(ReactiveElasticsearchClientCallback callback){
  return this.hostProvider.getActive(Verification.LAZY).flatMap(callback::doWithClient).onErrorResume(throwable -> {
    if (throwable instanceof ConnectException) {
      return hostProvider.getActive(Verification.ACTIVE).flatMap(callback::doWithClient);
    }
    return Mono.error(throwable);
  }
);
}
