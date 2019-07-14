private void toggleRecyclerViewAndEmptyStateVisibility(final @NonNull Boolean gone){
  ViewUtils.setGone(this.rewardStatsRecyclerView,gone);
  ViewUtils.setGone(this.emptyTextView,!gone);
}
