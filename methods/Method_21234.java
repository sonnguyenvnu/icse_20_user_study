private int getLayoutId(final @NonNull SectionRow sectionRow){
  if (objectFromSectionRow(sectionRow) instanceof DateTime) {
    return R.layout.message_center_timestamp_layout;
  }
 else   if (objectFromSectionRow(sectionRow) instanceof Message) {
    return R.layout.message_view;
  }
  return R.layout.empty_view;
}
