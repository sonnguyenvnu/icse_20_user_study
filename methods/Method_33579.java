/** 
 * ????&??adapter
 */
private void setAdapter(MovieDetailBean movieDetailBean){
  bindingContentView.xrvCast.setVisibility(View.VISIBLE);
  LinearLayoutManager mLayoutManager=new LinearLayoutManager(OneMovieDetailActivity.this);
  mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
  bindingContentView.xrvCast.setLayoutManager(mLayoutManager);
  bindingContentView.xrvCast.setPullRefreshEnabled(false);
  bindingContentView.xrvCast.setLoadingMoreEnabled(false);
  bindingContentView.xrvCast.setNestedScrollingEnabled(false);
  bindingContentView.xrvCast.setHasFixedSize(false);
  MovieDetailAdapter mAdapter=new MovieDetailAdapter();
  mAdapter.addAll(movieDetailBean.getDirectors());
  mAdapter.addAll(movieDetailBean.getCasts());
  bindingContentView.xrvCast.setAdapter(mAdapter);
}
