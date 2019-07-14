/** 
 * ????&??adapter
 */
private void setAdapter(MovieDetailBean movieDetailBean){
  binding.xrvCast.setVisibility(View.VISIBLE);
  LinearLayoutManager mLayoutManager=new LinearLayoutManager(MovieDetailActivity.this);
  mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
  binding.xrvCast.setLayoutManager(mLayoutManager);
  binding.xrvCast.setPullRefreshEnabled(false);
  binding.xrvCast.setLoadingMoreEnabled(false);
  binding.xrvCast.setNestedScrollingEnabled(false);
  binding.xrvCast.setHasFixedSize(false);
  MovieDetailAdapter mAdapter=new MovieDetailAdapter();
  mAdapter.addAll(movieDetailBean.getDirectors());
  mAdapter.addAll(movieDetailBean.getCasts());
  binding.xrvCast.setAdapter(mAdapter);
}
