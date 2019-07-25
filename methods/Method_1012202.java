/** 
 * ???????
 */
private void stb(){
  ArrayList<String> titles=new ArrayList<>();
  for (  String title : DemoDataProvider.titles) {
    titles.add(title);
  }
  stb.setSource(titles).setOnItemClickL(new BaseBanner.OnItemClickL(){
    @Override public void onItemClick(    int position){
      ToastUtils.toast("position--->" + position);
    }
  }
).startScroll();
}
