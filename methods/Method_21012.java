@Override public @NonNull Observable<User> fetchCurrentUser(){
  return this.service.currentUser().lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
