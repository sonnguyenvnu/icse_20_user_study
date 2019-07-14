private void showLayoutView(boolean isShow){
  if (isShow) {
    binding.nsSearchLayout.setVisibility(View.VISIBLE);
    binding.recyclerView.setVisibility(View.GONE);
  }
 else {
    binding.recyclerView.setVisibility(View.VISIBLE);
    binding.nsSearchLayout.setVisibility(View.GONE);
  }
}
