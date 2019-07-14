private void getHotFilm(){
  viewModel.getComingFilm().observe(this,new android.arch.lifecycle.Observer<ComingFilmBean>(){
    @Override public void onChanged(    @Nullable ComingFilmBean bookBean){
      if (bindingView.srlWan.isRefreshing()) {
        bindingView.srlWan.setRefreshing(false);
      }
      if (bookBean != null && bookBean.getMoviecomings() != null && bookBean.getMoviecomings().size() > 0) {
        if (viewModel.getStart() == 0) {
          showContentView();
          adapter.clear();
          adapter.notifyDataSetChanged();
        }
        int positionStart=adapter.getItemCount() + 1;
        ArrayList<ComingFilmBean.MoviecomingsBean> beans=new ArrayList<>();
        beans.addAll(bookBean.getAttention());
        beans.addAll(bookBean.getMoviecomings());
        Set<ComingFilmBean.MoviecomingsBean> set=new LinkedHashSet<>(beans);
        beans.clear();
        beans.addAll(set);
        adapter.addAll(beans);
        adapter.notifyItemRangeInserted(positionStart,beans.size());
        bindingView.xrvWan.refreshComplete();
        if (mIsFirst) {
          mIsFirst=false;
        }
      }
 else {
        if (adapter.getItemCount() == 0) {
          showError();
        }
 else {
          bindingView.xrvWan.noMoreLoading();
        }
      }
    }
  }
);
}
