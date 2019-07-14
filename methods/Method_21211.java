public void takeActivity(final @Nullable Activity activity){
  if (activity == null) {
    setSection(SECTION_ACTIVITY_SAMPLE_VIEW,Collections.emptyList());
  }
 else {
    setSection(SECTION_ACTIVITY_SAMPLE_VIEW,Collections.singletonList(activity));
  }
  notifyDataSetChanged();
}
