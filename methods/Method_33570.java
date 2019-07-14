private void showContent(HotMovieBean movieBean){
  showContentView();
  if (movieBean != null && movieBean.getSubjects() != null && movieBean.getSubjects().size() > 0) {
    if (oneBinding.tlMovie.getSelectedTabPosition() == 0) {
      setAdapter(movieBean);
      aCache.remove(Constants.ONE_HOT_MOVIE);
      aCache.put(Constants.ONE_HOT_MOVIE,movieBean);
      SPUtils.putString("one_data",TimeUtil.getData());
      mIsLoading=false;
    }
 else {
      if (viewModel.getStart() == 0) {
        oneAdapter.clear();
        oneAdapter.notifyDataSetChanged();
      }
      int positionStart=oneAdapter.getItemCount() + 2;
      oneAdapter.addAll(movieBean.getSubjects());
      oneAdapter.notifyItemRangeInserted(positionStart,movieBean.getSubjects().size());
      bindingView.listOne.refreshComplete();
    }
  }
 else {
    bindingView.listOne.refreshComplete();
    if (oneBinding.tlMovie.getSelectedTabPosition() == 0) {
      if (mHotMovieBean != null) {
        setAdapter(mHotMovieBean);
      }
 else       if (oneAdapter.getItemCount() == 0) {
        showError();
      }
    }
 else {
      if (viewModel.getStart() == 0) {
        ToastUtil.showToastLong("???????????~");
        oneBinding.tlMovie.setScrollPosition(0,0,true);
        oneBinding.tlMovie.getTabAt(0).select();
      }
      if (oneAdapter.getItemCount() == 0) {
        showError();
      }
 else {
        bindingView.listOne.noMoreLoading();
      }
    }
  }
}
