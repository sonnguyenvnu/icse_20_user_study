@Override public void bindView(final List<Menu> menuList){
  if (menuList == null || menuList.isEmpty()) {
    Log.e(TAG,"bindView  menuList == null || menuList.isEmpty() >> return;");
    return;
  }
  this.list=menuList;
  llBottomMenuViewMainItemContainer.removeAllViews();
  final int mainItemCount=list.size() > 4 ? 3 : list.size();
  Menu fsb;
  for (int i=0; i < mainItemCount; i++) {
    fsb=list.get(i);
    if (fsb.getImageRes() > 0) {
      addItem(false,i,fsb);
    }
 else {
      break;
    }
  }
  if (list.size() > 4) {
    addItem(true,-1,null);
    moreMenuNameList=new ArrayList<String>();
    moreMenuIntentCodeList=new ArrayList<Integer>();
    Menu moreFsb;
    for (int i=3; i < list.size(); i++) {
      moreFsb=list.get(i);
      if (moreFsb != null) {
        moreMenuNameList.add(moreFsb.getName());
        moreMenuIntentCodeList.add(moreFsb.getIntentCode());
      }
    }
  }
}
