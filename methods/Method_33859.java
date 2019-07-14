public void addHeaderView(View view){
  if (pullRefreshEnabled && !(mHeaderViews.get(0) instanceof YunRefreshHeader)) {
    YunRefreshHeader refreshHeader=new YunRefreshHeader(getContext());
    mHeaderViews.put(0,refreshHeader);
    mRefreshHeader=refreshHeader;
  }
  mHeaderViews.put(mHeaderViews.size(),view);
}
