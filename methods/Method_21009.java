@Override public @NonNull Observable<ActivityEnvelope> fetchActivities(final @Nullable Integer count){
  final List<String> categories=Arrays.asList(Activity.CATEGORY_BACKING,Activity.CATEGORY_CANCELLATION,Activity.CATEGORY_FAILURE,Activity.CATEGORY_LAUNCH,Activity.CATEGORY_SUCCESS,Activity.CATEGORY_UPDATE,Activity.CATEGORY_FOLLOW);
  return this.service.activities(categories,count).lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
