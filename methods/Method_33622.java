private void initRecyclerView(){
  mHeaderBinding=DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.header_item_everyday,null,false);
  FooterItemEverydayBinding mFooterBinding=DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.footer_item_everyday,null,false);
  bindingView.recyclerView.setPullRefreshEnabled(false);
  bindingView.recyclerView.setLoadingMoreEnabled(false);
  bindingView.recyclerView.addHeaderView(mHeaderBinding.getRoot());
  bindingView.recyclerView.addFootView(mFooterBinding.getRoot(),true);
  bindingView.recyclerView.noMoreLoading();
  bindingView.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
  bindingView.recyclerView.setNestedScrollingEnabled(false);
  bindingView.recyclerView.setHasFixedSize(false);
  bindingView.recyclerView.setItemAnimator(new DefaultItemAnimator());
  mEverydayAdapter=new EverydayAdapter();
  bindingView.recyclerView.setAdapter(mEverydayAdapter);
  String day=getTodayTime().get(2);
  mHeaderBinding.includeEveryday.tvDailyText.setText(day.indexOf("0") == 0 ? day.replace("0","") : day);
  mHeaderBinding.includeEveryday.ibXiandu.setOnClickListener(listener);
  mHeaderBinding.includeEveryday.ibWanAndroid.setOnClickListener(listener);
  mHeaderBinding.includeEveryday.ibMovieHot.setOnClickListener(listener);
  mHeaderBinding.includeEveryday.flEveryday.setOnClickListener(listener);
  DensityUtil.formatHeight(mHeaderBinding.banner,DensityUtil.getDisplayWidth(),2.5f,1);
  onObserveViewModel();
}
