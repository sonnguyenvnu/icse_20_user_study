private void init(){
  mLoadingFooter=new LoadingFooter(getContext());
  addFooterView(mLoadingFooter.getView());
  setOnScrollListener(this);
}
