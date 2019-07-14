@Override protected void onRefresh(){
  showContentView();
  showRotaLoading(true);
  viewModel.loadData();
}
