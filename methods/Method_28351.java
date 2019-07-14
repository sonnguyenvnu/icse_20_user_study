@Override public void onChangeForkCount(boolean isForked){
  long count=InputHelper.toLong(forkRepo);
  forkRepo.setText(numberFormat.format(isForked ? (count + 1) : (count > 0 ? (count - 1) : 0)));
  updatePinnedRepo();
}
