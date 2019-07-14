/** 
 * ???
 */
private void setAdapter(List<FilmDetailBean.ActorsBean> listBeans){
  LinearLayoutManager mLayoutManager=new LinearLayoutManager(FilmDetailActivity.this);
  mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
  bindingContentView.xrvCast.setLayoutManager(mLayoutManager);
  bindingContentView.xrvCast.setNestedScrollingEnabled(false);
  bindingContentView.xrvCast.setHasFixedSize(false);
  FilmDetailActorAdapter mAdapter=new FilmDetailActorAdapter();
  mAdapter.addAll(listBeans);
  bindingContentView.xrvCast.setAdapter(mAdapter);
  bindingContentView.xrvCast.setFocusable(false);
  bindingContentView.xrvCast.setFocusableInTouchMode(false);
}
