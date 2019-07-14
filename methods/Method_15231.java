@Override protected void setResult(){
  intent=new Intent();
  List<String> list=containerView.getSelectedItemList();
  if (list != null) {
    ArrayList<Integer> detailList=new ArrayList<Integer>();
    for (int i=0; i < list.size(); i++) {
      detailList.add(0 + Integer.valueOf(StringUtil.getNumber(list.get(i))));
    }
    Calendar calendar=Calendar.getInstance();
    calendar.set(0,0,0,detailList.get(0),detailList.get(1));
    intent.putExtra(RESULT_TIME_IN_MILLIS,calendar.getTimeInMillis());
    intent.putIntegerArrayListExtra(RESULT_TIME_DETAIL_LIST,detailList);
  }
  setResult(RESULT_OK,intent);
}
