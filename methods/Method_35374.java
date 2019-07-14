@Override public void loadLocalMusic(){
  mView.showProgress();
  mView.getLoaderManager().initLoader(URL_LOAD_LOCAL_MUSIC,null,this);
}
