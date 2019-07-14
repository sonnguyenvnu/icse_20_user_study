private void handleCache(){
  ArrayList<BannerItemBean> result=null;
  ArrayList<String> mBannerImages=null;
  try {
    mBannerImages=(ArrayList<String>)maCache.getAsObject(Constants.BANNER_PIC);
    result=(ArrayList<BannerItemBean>)maCache.getAsObject(Constants.BANNER_PIC_DATA);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  if (mBannerImages != null && mBannerImages.size() > 0) {
    bannerDataBean.setData(mBannerImages,result);
    bannerData.setValue(bannerDataBean);
  }
 else {
    showBannerPage();
  }
  mLists=(ArrayList<List<AndroidBean>>)maCache.getAsObject(Constants.EVERYDAY_CONTENT);
  if (mLists != null && mLists.size() > 0) {
    saveDate();
    contentData.setValue(mLists);
  }
 else {
    showRecyclerViewData();
  }
}
