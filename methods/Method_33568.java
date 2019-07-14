private void loadHotMovie(){
  viewModel.getHotMovie().observe(this,this::showContent);
}
