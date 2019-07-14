@Override public @NonNull Observable<ActivityEnvelope> fetchActivities(){
  return Observable.just(ActivityEnvelopeFactory.activityEnvelope(Collections.singletonList(ActivityFactory.activity())));
}
