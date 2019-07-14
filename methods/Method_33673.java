/** 
 * ??banner?
 */
public void showBannerView(List<WanAndroidBannerBean.DataBean> result){
  if (!isLoadBanner) {
    androidBinding.banner.setIndicatorRes(R.drawable.banner_red,R.drawable.banner_grey).setBannerAnimation(ScaleRightTransformer.class).setDelayTime(5000).setPages(result,CustomViewHolder::new).start();
    androidBinding.banner.stopAutoPlay();
    isLoadBanner=true;
  }
 else {
    androidBinding.banner.update(result);
  }
}
