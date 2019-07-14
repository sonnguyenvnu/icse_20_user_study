private void updateViewLayoutParams(View view,int width,int height){
  ViewGroup.LayoutParams layoutParams=view.getLayoutParams();
  if (layoutParams == null || layoutParams.height != width || layoutParams.width != height) {
    layoutParams=new AbsListView.LayoutParams(width,height);
    view.setLayoutParams(layoutParams);
  }
}
