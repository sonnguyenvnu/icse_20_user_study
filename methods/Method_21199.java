public void showLoggedOutEmptyState(final boolean show){
  setSection(SECTION_LOGGED_OUT_EMPTY_VIEW,show ? Collections.singletonList(false) : ListUtils.empty());
  notifyDataSetChanged();
}
