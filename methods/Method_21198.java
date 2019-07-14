public void showLoggedInEmptyState(final boolean show){
  setSection(SECTION_LOGGED_IN_EMPTY_VIEW,show ? Collections.singletonList(true) : ListUtils.empty());
  notifyDataSetChanged();
}
