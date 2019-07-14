@Override public void onMenuItemReselect(@IdRes int id,int position,boolean fromUser){
  sendToView(view -> view.onScrollTop(position));
}
