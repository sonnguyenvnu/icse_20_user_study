private void setPickerView(final int tabPosition){
  runThread(TAG + "setPickerView",new Runnable(){
    @Override public void run(){
      final ArrayList<Integer> selectedItemList=new ArrayList<Integer>();
      for (      GridPickerConfig gpcb : configList) {
        selectedItemList.add(0 + Integer.valueOf(StringUtil.getNumber(gpcb.getSelectedItemName())));
      }
      list=getList(tabPosition,selectedItemList);
      runUiThread(new Runnable(){
        @Override public void run(){
          containerView.bindView(tabPosition,list);
        }
      }
);
    }
  }
);
}
