@Override public void init(DetailView view){
  this.view=view;
  view.setPresenter(this);
  updateMediaRenderers();
}
