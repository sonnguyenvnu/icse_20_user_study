@Override public void bindData(final @Nullable Object data) throws Exception {
  final int unansweredSurveyCount=ObjectUtils.requireNonNull((int)data);
  if (unansweredSurveyCount > 0) {
    this.headerTextView.setText(this.ksString.format("Reward_Surveys",unansweredSurveyCount,"reward_survey_count",String.valueOf(unansweredSurveyCount)));
  }
}
