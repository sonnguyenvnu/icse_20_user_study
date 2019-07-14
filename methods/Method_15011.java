public void createView(BaseView<T> bv){
  this.bv=bv;
  removeAllViews();
  super.addView(bv.createView(context.getLayoutInflater()));
  bindView(null);
}
