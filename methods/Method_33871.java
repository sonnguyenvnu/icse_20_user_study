private void setVisiableHeight(int height){
  if (height < 0)   height=0;
  LayoutParams lp=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
  lp.height=height;
  mContainer.setLayoutParams(lp);
}
