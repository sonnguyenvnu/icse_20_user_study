private void initRefreshView(){
  bindingView.srlWan.setColorSchemeColors(CommonUtils.getColor(R.color.colorTheme));
  bindingView.srlWan.setOnRefreshListener(() -> bindingView.srlWan.postDelayed(() -> {
    bindingView.xrvWan.reset();
    getTree();
  }
,150));
  LinearLayoutManager layoutManager=new LinearLayoutManager(activity);
  bindingView.xrvWan.setLayoutManager(layoutManager);
  bindingView.xrvWan.setPullRefreshEnabled(false);
  bindingView.xrvWan.setLoadingMoreEnabled(false);
  bindingView.xrvWan.clearHeader();
  mTreeAdapter=new TreeAdapter();
  bindingView.xrvWan.setAdapter(mTreeAdapter);
  HeaderItemTreeBinding oneBinding=DataBindingUtil.inflate(getLayoutInflater(),R.layout.header_item_tree,null,false);
  bindingView.xrvWan.addHeaderView(oneBinding.getRoot());
  oneBinding.tvPosition.setOnClickListener(v -> layoutManager.scrollToPositionWithOffset(mTreeAdapter.mProjectPosition + 2,0));
}
