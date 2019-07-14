@Override public void onWorkOffline(){
  sendToView(BaseMvp.FAView::hideProgress);
}
