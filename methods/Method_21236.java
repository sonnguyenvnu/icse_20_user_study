protected @LayoutRes int layout(final @NonNull SectionRow sectionRow){
  if (sectionRow.section() == 0) {
    return R.layout.project_main_layout;
  }
 else {
    return R.layout.reward_view;
  }
}
