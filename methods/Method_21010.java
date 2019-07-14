@Override public @NonNull Observable<ActivityEnvelope> fetchActivitiesWithPaginationPath(final @NonNull String paginationPath){
  return this.service.activities(paginationPath).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
