private void addUrlList(List<List<AndroidBean>> lists,List<AndroidBean> arrayList,String typeTitle){
  AndroidBean bean=new AndroidBean();
  bean.setType_title(typeTitle);
  ArrayList<AndroidBean> androidBeen=new ArrayList<>();
  androidBeen.add(bean);
  lists.add(androidBeen);
  int androidSize=arrayList.size();
  if (androidSize > 0 && androidSize < 4) {
    lists.add(addUrlList(arrayList,androidSize));
  }
 else   if (androidSize >= 4) {
    ArrayList<AndroidBean> list1=new ArrayList<>();
    ArrayList<AndroidBean> list2=new ArrayList<>();
    for (int i=0; i < androidSize; i++) {
      if (i < 3) {
        list1.add(getAndroidBean(arrayList,i,androidSize));
      }
 else       if (i < 6) {
        list2.add(getAndroidBean(arrayList,i,androidSize));
      }
    }
    lists.add(list1);
    lists.add(list2);
  }
}
