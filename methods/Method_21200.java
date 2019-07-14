@Override protected @LayoutRes int layout(final @NonNull SectionRow sectionRow){
switch (sectionRow.section()) {
case SECTION_LOGGED_IN_EMPTY_VIEW:
    return R.layout.empty_activity_feed_view;
case SECTION_LOGGED_OUT_EMPTY_VIEW:
  return R.layout.empty_activity_feed_view;
case SECTION_SURVEYS_HEADER_VIEW:
return R.layout.activity_survey_header_view;
case SECTION_SURVEYS_VIEW:
return R.layout.activity_survey_view;
case SECTION_ACTIVITIES_VIEW:
return getActivityLayoutId(sectionRow);
}
return R.layout.empty_view;
}
