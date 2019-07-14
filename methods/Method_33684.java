private void initRefreshView(){
  layoutManager=new LinearLayoutManager(activity);
  bindingView.xrvNavi.setLayoutManager(layoutManager);
  mNaviAdapter=new NaviAdapter();
  bindingView.xrvNavi.setAdapter(mNaviAdapter);
  LinearLayoutManager layoutManager2=new LinearLayoutManager(activity);
  bindingView.xrvNaviDetail.setLayoutManager(layoutManager2);
  mContentAdapter=new NaviContentAdapter();
  bindingView.xrvNaviDetail.setAdapter(mContentAdapter);
  mNaviAdapter.setOnSelectListener(new NaviAdapter.OnSelectListener(){
    @Override public void onSelected(    int position){
      selectItem(position);
      moveToCenter(position);
      layoutManager2.scrollToPositionWithOffset(position,0);
    }
  }
);
  bindingView.xrvNaviDetail.addOnScrollListener(new RecyclerView.OnScrollListener(){
    @Override public void onScrolled(    RecyclerView recyclerView,    int dx,    int dy){
      super.onScrolled(recyclerView,dx,dy);
      int itemPosition=layoutManager2.findFirstVisibleItemPosition();
      if (currentPosition != itemPosition) {
        selectItem(itemPosition);
        moveToCenter(itemPosition);
      }
    }
  }
);
}
