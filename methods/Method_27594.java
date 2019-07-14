@Override public void onRefresh(){
  if (!InputHelper.isEmpty(query)) {
    getPresenter().onCallApi(1,query);
  }
}
