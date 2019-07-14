private void initRecycleView(){
  bindingView.xrvWelfare.setPullRefreshEnabled(false);
  bindingView.xrvWelfare.clearHeader();
  mWelfareAdapter=new WelfareAdapter();
  bindingView.xrvWelfare.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
  bindingView.xrvWelfare.setHasFixedSize(true);
  bindingView.xrvWelfare.setItemAnimator(null);
  bindingView.xrvWelfare.setAdapter(mWelfareAdapter);
  bindingView.xrvWelfare.setLoadingListener(new XRecyclerView.LoadingListener(){
    @Override public void onRefresh(){
    }
    @Override public void onLoadMore(){
      int page=viewModel.getPage();
      page++;
      viewModel.setPage(page);
      loadWelfareData();
    }
  }
);
  mWelfareAdapter.setOnItemClickListener((resultsBean,position) -> {
    ViewBigImageActivity.startImageList(getContext(),position,imgList,imgTitleList);
  }
);
  viewModel.getImageAndTitle().observe(this,new Observer<ArrayList<ArrayList<String>>>(){
    @Override public void onChanged(    @Nullable ArrayList<ArrayList<String>> arrayLists){
      if (arrayLists != null && arrayLists.size() == 2) {
        imgList.addAll(arrayLists.get(0));
        imgTitleList.addAll(arrayLists.get(1));
      }
    }
  }
);
}
