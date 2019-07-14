@Override public void startProgress(int newProgress){
  mProgressBar.setVisibility(View.VISIBLE);
  mProgressBar.setProgress(newProgress);
  if (newProgress == 100) {
    mProgressBar.setVisibility(View.GONE);
  }
}
