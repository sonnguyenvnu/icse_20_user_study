/** 
 * ??
 */
private void setImageAdapter(List<FilmDetailBean.ImageListBean> listBeans){
  bindingContentView.xrvImages.setVisibility(View.VISIBLE);
  LinearLayoutManager mLayoutManager=new LinearLayoutManager(FilmDetailActivity.this);
  mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
  bindingContentView.xrvImages.setLayoutManager(mLayoutManager);
  bindingContentView.xrvImages.setNestedScrollingEnabled(false);
  bindingContentView.xrvImages.setHasFixedSize(false);
  FilmDetailImageAdapter mAdapter=new FilmDetailImageAdapter();
  mAdapter.addAll(listBeans);
  bindingContentView.xrvImages.setAdapter(mAdapter);
  bindingContentView.xrvImages.setFocusable(false);
  bindingContentView.xrvImages.setFocusableInTouchMode(false);
}
