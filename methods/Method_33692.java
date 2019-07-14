private void getTree(){
  viewModel.getTree().observe(this,new android.arch.lifecycle.Observer<TreeBean>(){
    @Override public void onChanged(    @Nullable TreeBean treeBean){
      showContentView();
      if (bindingView.srlWan.isRefreshing()) {
        bindingView.srlWan.setRefreshing(false);
      }
      if (treeBean != null && treeBean.getData() != null && treeBean.getData().size() > 0) {
        mTreeAdapter.clear();
        mTreeAdapter.addAll(treeBean.getData());
        mTreeAdapter.notifyDataSetChanged();
        bindingView.xrvWan.refreshComplete();
        mIsFirst=false;
      }
 else {
        if (mIsFirst) {
          showError();
        }
 else {
          bindingView.xrvWan.refreshComplete();
        }
      }
    }
  }
);
}
