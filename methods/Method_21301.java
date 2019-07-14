private void toggleRecyclerViewAndEmptyStateVisibility(final @NonNull Boolean gone){
  ViewUtils.setGone(this.referrerStatsRecyclerView,gone);
  ViewUtils.setGone(this.emptyTextView,!gone);
}
