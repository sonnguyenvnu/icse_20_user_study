@Override public void bindData(final @Nullable Object data) throws Exception {
  final SurveyResponse surveyResponse=requireNonNull((SurveyResponse)data);
  this.viewModel.inputs.configureWith(surveyResponse);
}
