@Override protected @LayoutRes int layout(final @NonNull SectionRow sectionRow){
  if (sectionRow.section() == SECTION_ONBOARDING_VIEW) {
    return R.layout.discovery_onboarding_view;
  }
 else   if (sectionRow.section() == SECTION_ACTIVITY_SAMPLE_VIEW) {
    if (objectFromSectionRow(sectionRow) instanceof Activity) {
      final Activity activity=(Activity)objectFromSectionRow(sectionRow);
      if (activity.category().equals(Activity.CATEGORY_BACKING)) {
        return R.layout.activity_sample_friend_backing_view;
      }
 else       if (activity.category().equals(Activity.CATEGORY_FOLLOW)) {
        return R.layout.activity_sample_friend_follow_view;
      }
 else {
        return R.layout.activity_sample_project_view;
      }
    }
    return R.layout.empty_view;
  }
 else {
    return R.layout.project_card_view;
  }
}
