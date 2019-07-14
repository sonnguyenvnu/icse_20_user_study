private void initRecyclerView(){
  oneBinding=DataBindingUtil.inflate(getLayoutInflater(),R.layout.header_item_one,null,false);
  oneBinding.setView(this);
  bindingView.listOne.setLayoutManager(new LinearLayoutManager(activity));
  bindingView.listOne.setPullRefreshEnabled(false);
  bindingView.listOne.clearHeader();
  bindingView.listOne.setItemAnimator(null);
  bindingView.listOne.addHeaderView(oneBinding.getRoot());
  oneAdapter=new OneAdapter(activity);
  bindingView.listOne.setAdapter(oneAdapter);
  bindingView.listOne.setLoadingListener(new XRecyclerView.LoadingListener(){
    @Override public void onRefresh(){
    }
    @Override public void onLoadMore(){
      if (oneBinding.tlMovie.getSelectedTabPosition() == 1) {
        viewModel.handleNextStart();
        loadComingSoonMovie();
      }
 else {
        bindingView.listOne.noMoreLoading();
      }
    }
  }
);
  oneBinding.tlMovie.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
    @Override public void onTabSelected(    TabLayout.Tab tab){
      int tabPosition=oneBinding.tlMovie.getSelectedTabPosition();
      if (tabPosition == 0) {
        viewModel.setStart(0);
        bindingView.listOne.reset();
        loadHotMovie();
      }
 else {
        viewModel.setStart(0);
        bindingView.listOne.reset();
        loadComingSoonMovie();
      }
    }
    @Override public void onTabUnselected(    TabLayout.Tab tab){
    }
    @Override public void onTabReselected(    TabLayout.Tab tab){
    }
  }
);
}
