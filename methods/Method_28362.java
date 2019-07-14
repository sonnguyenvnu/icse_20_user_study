private void updatePinnedRepo(){
  getPresenter().updatePinned((int)InputHelper.toLong(forkRepo),(int)InputHelper.toLong(starRepo),(int)InputHelper.toLong(watchRepo));
}
