@Override public void onProgress(int progress){
  if (progress < 0 || progress > 100 || mProgressPieView == null) {
    return;
  }
  mProgressPieView.setProgress(progress);
  mProgressPieView.setText(String.format(Locale.getDefault(),"%d%%",progress));
}
