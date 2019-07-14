private void initData(){
  bindingView.recyclerView.postDelayed(new Runnable(){
    @Override public void run(){
      ArrayList<String> types=new ArrayList<>();
      types.addAll(Arrays.asList(BookApiUtils.TagTitles));
      types.addAll(Arrays.asList(BookApiUtils.HomeTag));
      types.addAll(Arrays.asList(BookApiUtils.LiterTag));
      types.addAll(Arrays.asList(BookApiUtils.CoderTag));
      types.addAll(Arrays.asList(BookApiUtils.PopularTag));
      types.addAll(Arrays.asList(BookApiUtils.CultureTag));
      types.addAll(Arrays.asList(BookApiUtils.LifeTag));
      types.addAll(Arrays.asList(BookApiUtils.FinancialTag));
      mAdapter.addData(types);
      mAdapter.notifyDataSetChanged();
      showContentView();
    }
  }
,100);
}
