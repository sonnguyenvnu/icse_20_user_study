private void setPickerView(final int tabPosition,final int itemPositon){
  runThread(TAG + "setPickerView",new Runnable(){
    @Override public void run(){
      list=getList(tabPosition,containerView.getSelectedItemList());
      runUiThread(new Runnable(){
        @Override public void run(){
          containerView.bindView(tabPosition,list,itemPositon);
        }
      }
);
    }
  }
);
}
