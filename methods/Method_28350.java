@Override public void onChangeStarCount(boolean isStarred){
  long count=InputHelper.toLong(starRepo);
  starRepo.setText(numberFormat.format(isStarred ? (count + 1) : (count > 0 ? (count - 1) : 0)));
  updatePinnedRepo();
}
