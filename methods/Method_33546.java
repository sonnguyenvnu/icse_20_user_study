private void refresh(boolean isChecked,String mType){
  if (isChecked) {
    bindingView.srlWan.setRefreshing(true);
    bindingView.xrvWan.reset();
    viewModel.setStart(0);
    viewModel.bookType.set(mType);
    getBook();
  }
}
