@Override public @NonNull Observable<List<SurveyResponse>> fetchUnansweredSurveys(){
  return this.service.unansweredSurveys().lift(apiErrorOperator()).subscribeOn(Schedulers.io());
}
