@Override public int getScroll(){
  View contentView=getContentView();
  if (contentView instanceof FlexibleSpaceContentView) {
    return ((FlexibleSpaceContentView)contentView).getScroll();
  }
 else {
    return contentView.getScrollY();
  }
}
