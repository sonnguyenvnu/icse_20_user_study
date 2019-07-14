public Observable<InternalBuildEnvelope> pingBeta(){
  return this.service.pingBeta().filter(Response::isSuccessful).map(Response::body).subscribeOn(Schedulers.io());
}
