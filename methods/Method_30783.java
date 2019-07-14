@Override public void scrollTo(int scroll){
  View contentView=getContentView();
  if (contentView instanceof FlexibleSpaceContentView) {
    ((FlexibleSpaceContentView)contentView).scrollTo(scroll);
  }
 else {
    contentView.scrollTo(0,scroll);
  }
}
