private void initViewModel(int position){
  if (position == 0) {
    mAdapter=new CategoryArticleAdapter(this);
    MyDividerItemDecoration itemDecoration=new MyDividerItemDecoration(binding.recyclerView.getContext(),DividerItemDecoration.VERTICAL,false);
    itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(binding.recyclerView.getContext(),R.drawable.shape_line)));
    binding.recyclerView.addItemDecoration(itemDecoration);
    binding.recyclerView.setAdapter(mAdapter);
  }
 else   if (position == 1 || position == 2) {
    mAdapter=new GankAndroidSearchAdapter(this);
    MyDividerItemDecoration itemDecoration=new MyDividerItemDecoration(binding.recyclerView.getContext(),DividerItemDecoration.VERTICAL,false);
    itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(binding.recyclerView.getContext(),R.drawable.shape_remove)));
    binding.recyclerView.addItemDecoration(itemDecoration);
    binding.recyclerView.setAdapter(mAdapter);
  }
  mAdapter.setOnLoadMoreListener(() -> {
    int position2=binding.tlSearch.getSelectedTabPosition();
    if (position2 == 0) {
      int page=viewModel.getPage();
      viewModel.setPage(++page);
      loadWanData();
    }
 else     if (position2 == 1 || position2 == 2) {
      int page=viewModel.getGankPage();
      viewModel.setGankPage(++page);
      loadGankData();
    }
  }
,binding.recyclerView);
  if (!TextUtils.isEmpty(keyWord)) {
    load();
  }
}
