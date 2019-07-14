public void takeActivities(final @NonNull List<Activity> activities){
  setSection(SECTION_ACTIVITIES_VIEW,activities);
  notifyDataSetChanged();
}
