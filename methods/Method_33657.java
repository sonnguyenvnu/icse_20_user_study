private void loadData(){
  viewModel.getHomeList(cid).observe(this,this::showContent);
}
