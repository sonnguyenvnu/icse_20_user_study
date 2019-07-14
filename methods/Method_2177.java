@Override public void destroyItem(ViewGroup container,int position,Object object){
  FrameLayout page=(FrameLayout)container.getChildAt(position);
  ZoomableDraweeView zoomableDraweeView=(ZoomableDraweeView)page.getChildAt(0);
  zoomableDraweeView.setController(null);
}
