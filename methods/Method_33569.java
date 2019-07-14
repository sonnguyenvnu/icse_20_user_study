private void loadComingSoonMovie(){
  viewModel.getComingSoon().observe(this,this::showContent);
}
