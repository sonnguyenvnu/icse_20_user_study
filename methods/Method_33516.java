private AndroidBean getAndroidBean(List<AndroidBean> arrayList,int i,int androidSize){
  AndroidBean androidBean=new AndroidBean();
  androidBean.setDesc(arrayList.get(i).getDesc());
  androidBean.setType(arrayList.get(i).getType());
  androidBean.setUrl(arrayList.get(i).getUrl());
  if (i < 3) {
    androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);
  }
 else   if (androidSize == 4) {
    androidBean.setImage_url(ConstantsImageUrl.HOME_ONE_URLS[getRandom(1)]);
  }
 else   if (androidSize == 5) {
    androidBean.setImage_url(ConstantsImageUrl.HOME_TWO_URLS[getRandom(2)]);
  }
 else   if (androidSize >= 6) {
    androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);
  }
  return androidBean;
}
