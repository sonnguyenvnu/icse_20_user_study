private void onPageScrolled(int position,int width,int positionOffsetPixels){
  if (delegate == null) {
    return;
  }
  if (position == 1) {
    delegate.onTabOpened(positionOffsetPixels != 0 ? 2 : 0);
  }
 else   if (position == 2) {
    delegate.onTabOpened(3);
  }
 else {
    delegate.onTabOpened(0);
  }
}
