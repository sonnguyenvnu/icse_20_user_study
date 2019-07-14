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
          if (tabPosition < 2) {
            ArrayList<String> selectedList=containerView.getSelectedItemList();
            if (selectedList != null && selectedList.size() >= 3) {
              if (TimeUtil.isLeapYear(0 + Integer.valueOf(StringUtil.getNumber(selectedList.get(0)))) == false) {
                if ("2".equals(StringUtil.getNumber(selectedList.get(1))) && "29".equals(StringUtil.getNumber(selectedList.get(2)))) {
                  onItemSelectedListener.onItemSelected(null,null,containerView.getCurrentSelectedItemPosition(),0);
                }
              }
            }
          }
        }
      }
);
    }
  }
);
}
