@Override public void onOffsetChanged(AppBarLayout appBarLayout,int verticalOffset){
  verticalOffset=Math.abs(verticalOffset);
  if (verticalOffset == 0 || verticalOffset == appBarLayout.getTotalScrollRange())   isAppBarMoving=false;
}
