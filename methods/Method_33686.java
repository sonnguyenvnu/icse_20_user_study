private void loadCustomData(){
  viewModel.getNaviJson().observe(this,new android.arch.lifecycle.Observer<NaviJsonBean>(){
    @Override public void onChanged(    @Nullable NaviJsonBean naviJsonBean){
      if (naviJsonBean != null && naviJsonBean.getData() != null && naviJsonBean.getData().size() > 0) {
        showContentView();
        mNaviAdapter.clear();
        mNaviAdapter.addAll(naviJsonBean.getData());
        mNaviAdapter.notifyDataSetChanged();
        selectItem(0);
        mContentAdapter.clear();
        mContentAdapter.addAll(naviJsonBean.getData());
        mContentAdapter.notifyDataSetChanged();
        mIsFirst=false;
      }
 else {
        showError();
      }
    }
  }
);
}
