@Override public boolean onLoadMore(int page,int totalItemsCount){
  if (presenter != null) {
    presenter.setPreviousTotal(totalItemsCount);
    return presenter.onCallApi(page + 1,parameter);
  }
  return false;
}
