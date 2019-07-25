private void retry(List<ActionRecord> batch){
  batch.forEach(this::retry);
}
