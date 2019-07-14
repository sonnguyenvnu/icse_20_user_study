public void takeSurveys(final @NonNull List<SurveyResponse> surveyResponses){
  if (surveyResponses.size() > 0) {
    setSection(SECTION_SURVEYS_HEADER_VIEW,Collections.singletonList(surveyResponses.size()));
    setSection(SECTION_SURVEYS_VIEW,surveyResponses);
  }
 else {
    setSection(SECTION_SURVEYS_HEADER_VIEW,Collections.emptyList());
    setSection(SECTION_SURVEYS_VIEW,Collections.emptyList());
  }
  notifyDataSetChanged();
}
