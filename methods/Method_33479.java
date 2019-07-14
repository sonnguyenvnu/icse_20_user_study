/** 
 * *** ??????? ????? 
 * @param imgUrl    header???????imageUrl
 * @param mHeaderBg header??????ImageView??
 */
protected void initSlideShapeTheme(String imgUrl,ImageView mHeaderBg){
  setImgHeaderBg(imgUrl);
  int toolbarHeight=bindingTitleView.tbBaseTitle.getLayoutParams().height;
  final int headerBgHeight=toolbarHeight + StatusBarUtil.getStatusBarHeight(this);
  ViewGroup.LayoutParams params=bindingTitleView.ivBaseTitlebarBg.getLayoutParams();
  ViewGroup.MarginLayoutParams ivTitleHeadBgParams=(ViewGroup.MarginLayoutParams)bindingTitleView.ivBaseTitlebarBg.getLayoutParams();
  int marginTop=params.height - headerBgHeight;
  ivTitleHeadBgParams.setMargins(0,-marginTop,0,0);
  bindingTitleView.ivBaseTitlebarBg.setImageAlpha(0);
  StatusBarUtils.setTranslucentImageHeader(this,0,bindingTitleView.tbBaseTitle);
  if (mHeaderBg != null) {
    ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)mHeaderBg.getLayoutParams();
    layoutParams.setMargins(0,-StatusBarUtil.getStatusBarHeight(this),0,0);
    ViewGroup.LayoutParams imgItemBgparams=mHeaderBg.getLayoutParams();
    imageBgHeight=imgItemBgparams.height;
  }
  initScrollViewListener();
  initNewSlidingParams();
}
