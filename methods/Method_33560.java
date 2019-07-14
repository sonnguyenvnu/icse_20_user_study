/** 
 * ???????
 */
private void initSlideShapeTheme(){
  setImgHeaderBg();
  int toolbarHeight=binding.titleToolBar.getLayoutParams().height;
  Log.i(TAG,"toolbar height:" + toolbarHeight);
  final int headerBgHeight=toolbarHeight + getStatusBarHeight(this);
  Log.i(TAG,"headerBgHeight:" + headerBgHeight);
  ViewGroup.LayoutParams params=binding.ivTitleHeadBg.getLayoutParams();
  ViewGroup.MarginLayoutParams ivTitleHeadBgParams=(ViewGroup.MarginLayoutParams)binding.ivTitleHeadBg.getLayoutParams();
  int marginTop=params.height - headerBgHeight;
  ivTitleHeadBgParams.setMargins(0,-marginTop,0,0);
  binding.ivTitleHeadBg.setImageAlpha(0);
  StatusBarUtils.setTranslucentImageHeader(this,0,binding.titleToolBar);
  if (binding.include.imgItemBg != null) {
    ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)binding.include.imgItemBg.getLayoutParams();
    layoutParams.setMargins(0,-StatusBarUtil.getStatusBarHeight(this),0,0);
  }
  ViewGroup.LayoutParams imgItemBgparams=binding.include.imgItemBg.getLayoutParams();
  imageBgHeight=imgItemBgparams.height;
  initScrollViewListener();
  initNewSlidingParams();
}
