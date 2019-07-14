private List<AndroidBean> addUrlList(List<AndroidBean> arrayList,int androidSize){
  List<AndroidBean> tempList=new ArrayList<>();
  for (int i=0; i < androidSize; i++) {
    AndroidBean androidBean=new AndroidBean();
    androidBean.setDesc(arrayList.get(i).getDesc());
    androidBean.setType(arrayList.get(i).getType());
    androidBean.setUrl(arrayList.get(i).getUrl());
    if (androidSize == 1) {
      androidBean.setImage_url(ConstantsImageUrl.HOME_ONE_URLS[getRandom(1)]);
    }
 else     if (androidSize == 2) {
      androidBean.setImage_url(ConstantsImageUrl.HOME_TWO_URLS[getRandom(2)]);
    }
 else     if (androidSize == 3) {
      androidBean.setImage_url(ConstantsImageUrl.HOME_SIX_URLS[getRandom(3)]);
    }
    tempList.add(androidBean);
  }
  return tempList;
}
