@Override public void show(int height,boolean immediate){
  ViewGroup.LayoutParams layoutParams=getLayoutParams();
  layoutParams.height=height;
  getChildAt(0).setVisibility(VISIBLE);
  setVisibility(VISIBLE);
}
