protected @LayoutRes int layout(final @NonNull SectionRow sectionRow){
switch (sectionRow.section()) {
case SECTION_POPULAR_TITLE:
    return R.layout.search_popular_title_view;
case SECTION_FEATURED_PROJECT:
  return R.layout.featured_search_result_view;
case SECTION_PROJECT:
return R.layout.project_search_result_view;
default :
throw new IllegalStateException("Invalid section row");
}
}
