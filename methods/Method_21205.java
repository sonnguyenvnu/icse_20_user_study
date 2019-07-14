@Override protected int layout(final @NonNull SectionRow sectionRow){
switch (sectionRow.section()) {
case SECTION_PROJECTS_HEADER:
    return R.layout.creator_dashboard_project_switcher_header;
case SECTION_PROJECTS:
  return R.layout.creator_dashboard_project_switcher_view;
}
return R.layout.empty_view;
}
