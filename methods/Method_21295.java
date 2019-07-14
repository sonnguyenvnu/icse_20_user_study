private void hideReferrer(final boolean gone,final @NonNull View layout,final @NonNull View bar,final @NonNull View indicator){
  ViewUtils.setGone(layout,gone);
  ViewUtils.setGone(bar,gone);
  ViewUtils.setGone(indicator,gone);
}
