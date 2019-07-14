@Override public void onInitContributions(boolean show){
  if (contributionView == null)   return;
  if (show) {
    contributionView.onResponse();
  }
  contributionCard.setVisibility(show ? VISIBLE : GONE);
  contributionsCaption.setVisibility(show ? VISIBLE : GONE);
}
