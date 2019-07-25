@Override public void update(int progress){
  if (this.state == InternalState.FINISHED) {
    throw new IllegalStateException("Update is finished.");
  }
  if (progress < 0 || progress > 100) {
    throw new IllegalArgumentException("The progress must be between 0 and 100.");
  }
  if (this.progress == null) {
    updateProgress(progress);
  }
 else   if (progress < this.progress) {
    throw new IllegalArgumentException("The new progress must not be smaller than the old progress.");
  }
 else   if (this.progress != progress) {
    updateProgress(progress);
  }
}
