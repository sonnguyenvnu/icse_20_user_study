@Override public void dispose(){
  if (updateJob != null) {
    updateJob.cancel(true);
  }
  updateJob=null;
  radio=null;
}
