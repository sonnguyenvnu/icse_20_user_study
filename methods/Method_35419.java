private void updateProgressTextWithProgress(int progress){
  int targetDuration=getDuration(progress);
  textViewProgress.setText(TimeUtils.formatDuration(targetDuration));
}
