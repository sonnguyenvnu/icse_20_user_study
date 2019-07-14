private @Nullable Observable<Pair<PushNotificationEnvelope,SurveyResponse>> fetchSurveyResponseWithEnvelope(final @NonNull PushNotificationEnvelope envelope){
  final PushNotificationEnvelope.Survey survey=envelope.survey();
  if (survey == null) {
    return null;
  }
  final Observable<SurveyResponse> surveyResponse=this.client.fetchSurveyResponse(survey.id()).compose(neverError());
  return Observable.just(envelope).compose(combineLatestPair(surveyResponse));
}
