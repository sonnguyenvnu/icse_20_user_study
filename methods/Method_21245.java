protected @LayoutRes int layout(final @NonNull SectionRow sectionRow){
  if (sectionRow.section() == SECTION_SHARE_VIEW) {
    return R.layout.thanks_share_view;
  }
 else   if (sectionRow.section() == SECTION_RECOMMENDED_PROJECTS_VIEW) {
    return R.layout.project_card_view;
  }
 else {
    return R.layout.thanks_category_view;
  }
}
