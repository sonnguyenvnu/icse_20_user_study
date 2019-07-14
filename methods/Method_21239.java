@Override protected int layout(final @NonNull SectionRow sectionRow){
  if (sectionRow.section() == 0) {
    return R.layout.project_context_view;
  }
 else {
    return R.layout.project_social_view;
  }
}
