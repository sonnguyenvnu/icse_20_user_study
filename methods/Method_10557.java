private void initData(){
  listYearMonth.clear();
  Calendar calendar=Calendar.getInstance(Locale.CHINA);
  for (int i=calendar.get(Calendar.YEAR) - 3; i <= calendar.get(Calendar.YEAR) + 2; i++) {
    for (int j=1; j <= 12; j++) {
      listYearMonth.add(i + "?" + j + "?");
    }
  }
  String[] arr=listYearMonth.toArray(new String[listYearMonth.size()]);
  int CurrentIndex=0;
  for (int i=0; i < arr.length; i++) {
    if (arr[i].equals(calendar.get(Calendar.YEAR) + "?" + (calendar.get(Calendar.MONTH) + 1) + "?")) {
      CurrentIndex=i;
      break;
    }
  }
  ArrayWheelAdapter<String> ampmAdapter=new ArrayWheelAdapter<String>(this,arr);
  ampmAdapter.setItemResource(R.layout.item_wheel_year_month);
  ampmAdapter.setItemTextResource(R.id.tv_year);
  mWheelViewYearMonth.setViewAdapter(ampmAdapter);
  mWheelViewYearMonth.setCurrentItem(CurrentIndex);
  mWheelViewYearMonth.addScrollingListener(new OnWheelScrollListener(){
    @Override public void onScrollingStarted(    AbstractWheel wheel){
      before=listYearMonth.get(wheel.getCurrentItem());
    }
    @Override public void onScrollingFinished(    AbstractWheel wheel){
      behind=listYearMonth.get(wheel.getCurrentItem());
      Log.v("addScrollingListener","listYearMonth:" + listYearMonth.get(wheel.getCurrentItem()));
      if (!before.equals(behind)) {
        int year=RxDataTool.stringToInt(listYearMonth.get(wheel.getCurrentItem()).substring(0,4));
        int month=RxDataTool.stringToInt(listYearMonth.get(wheel.getCurrentItem()).substring(5,6));
      }
    }
  }
);
  mWheelViewYearMonth.addClickingListener(new OnWheelClickedListener(){
    @Override public void onItemClicked(    AbstractWheel wheel,    int itemIndex){
      Log.v("addScrollingListener","listYearMonth:" + listYearMonth.get(itemIndex));
      mWheelViewYearMonth.setCurrentItem(itemIndex,true);
    }
  }
);
}
