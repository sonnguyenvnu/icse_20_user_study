/** 
 * banner??
 */
private void showBannerPage(){
  mEverydayModel.showBannerPage(new RequestImpl(){
    @Override public void loadSuccess(    Object object){
      FrontpageBean bean=(FrontpageBean)object;
      if (bean != null && bean.getResult() != null && bean.getResult().getFocus() != null && bean.getResult().getFocus().getResult() != null) {
        final ArrayList<BannerItemBean> result=(ArrayList<BannerItemBean>)bean.getResult().getFocus().getResult();
        ArrayList<String> mBannerImages=new ArrayList<String>();
        if (result != null && result.size() > 0) {
          for (int i=0; i < result.size(); i++) {
            mBannerImages.add(result.get(i).getRandpic());
          }
          maCache.remove(Constants.BANNER_PIC);
          maCache.put(Constants.BANNER_PIC,mBannerImages);
          maCache.remove(Constants.BANNER_PIC_DATA);
          maCache.put(Constants.BANNER_PIC_DATA,result);
          bannerDataBean.setData(mBannerImages,result);
          bannerData.setValue(bannerDataBean);
        }
      }
    }
    @Override public void loadFailed(){
    }
    @Override public void addSubscription(    Disposable subscription){
      addDisposable(subscription);
    }
  }
);
}
