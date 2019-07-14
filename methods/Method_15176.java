@Override protected void setResult(){
  intent=new Intent();
  List<String> list=containerView.getSelectedItemList();
  if (list != null && list.size() >= 3) {
    ArrayList<Integer> detailList=new ArrayList<Integer>();
    for (int i=0; i < list.size(); i++) {
      detailList.add(0 + Integer.valueOf(StringUtil.getNumber(list.get(i))));
    }
    detailList.set(1,detailList.get(1) - 1);
    Calendar calendar=Calendar.getInstance();
    calendar.set(detailList.get(0),detailList.get(1),detailList.get(2));
    intent.putExtra(RESULT_TIME_IN_MILLIS,calendar.getTimeInMillis());
    intent.putIntegerArrayListExtra(RESULT_DATE_DETAIL_LIST,detailList);
  }
  setResult(RESULT_OK,intent);
}
