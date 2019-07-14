private int getActivityLayoutId(final @NonNull SectionRow sectionRow){
  if (objectFromSectionRow(sectionRow) instanceof Activity) {
    final Activity activity=(Activity)objectFromSectionRow(sectionRow);
switch (activity.category()) {
case Activity.CATEGORY_BACKING:
      return R.layout.activity_friend_backing_view;
case Activity.CATEGORY_FOLLOW:
    return R.layout.activity_friend_follow_view;
case Activity.CATEGORY_FAILURE:
case Activity.CATEGORY_CANCELLATION:
case Activity.CATEGORY_SUSPENSION:
  return R.layout.activity_project_state_changed_view;
case Activity.CATEGORY_LAUNCH:
case Activity.CATEGORY_SUCCESS:
return R.layout.activity_project_state_changed_positive_view;
case Activity.CATEGORY_UPDATE:
return R.layout.activity_project_update_view;
}
}
return R.layout.empty_view;
}
