private void initRefreshView(){
  String type=getIntent().getStringExtra("type");
  GridLayoutManager mLayoutManager=new GridLayoutManager(this,4);
  bindingView.recyclerView.setLayoutManager(mLayoutManager);
  bindingView.recyclerView.setItemAnimator(null);
  mAdapter=new BookTypeAdapter();
  mAdapter.setType(type);
  mAdapter.bindToRecyclerView(bindingView.recyclerView,true);
  mAdapter.setLoaded(false);
  mAdapter.setEnableLoadMore(false);
  MyDividerItemDecoration itemDecoration=new MyDividerItemDecoration(bindingView.recyclerView.getContext(),DividerItemDecoration.VERTICAL,false);
  itemDecoration.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(bindingView.recyclerView.getContext(),R.drawable.shape_line)));
  bindingView.recyclerView.addItemDecoration(itemDecoration);
  mAdapter.setOnSelectListener(new BookTypeAdapter.OnSelectListener(){
    @Override public void onSelected(    String type){
      DebugUtil.error(type);
      Intent intent=new Intent();
      intent.putExtra("type",type);
      setResult(10,intent);
      finish();
    }
  }
);
}
