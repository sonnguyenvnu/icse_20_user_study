@Override public void onSuccessDeleted(){
  hideProgress();
  if (getPresenter().getGist() != null) {
    Intent intent=new Intent();
    Gist gistsModel=new Gist();
    gistsModel.setUrl(getPresenter().getGist().getHtmlUrl());
    intent.putExtras(Bundler.start().put(BundleConstant.ITEM,gistsModel).end());
    setResult(RESULT_OK,intent);
  }
  finish();
}
