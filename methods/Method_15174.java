@SuppressLint("ResourceAsColor") private synchronized List<Entry<Integer,String>> getList(int tabPosition,ArrayList<Integer> selectedItemList){
  int level=TimeUtil.LEVEL_YEAR + tabPosition;
  if (selectedItemList == null || selectedItemList.size() != 3 || TimeUtil.isContainLevel(level) == false) {
    return null;
  }
  list=new ArrayList<Entry<Integer,String>>();
  Calendar calendar=Calendar.getInstance();
  calendar.set(selectedItemList.get(0),selectedItemList.get(1) - 1,1);
switch (level) {
case TimeUtil.LEVEL_YEAR:
    for (int i=0; i < maxDateDetails[0] - minDateDetails[0]; i++) {
      list.add(new Entry<Integer,String>(GridPickerAdapter.TYPE_CONTNET_ENABLE,String.valueOf(i + 1 + minDateDetails[0])));
    }
  break;
case TimeUtil.LEVEL_MONTH:
for (int i=0; i < 12; i++) {
  list.add(new Entry<Integer,String>(GridPickerAdapter.TYPE_CONTNET_ENABLE,String.valueOf(i + 1)));
}
break;
case TimeUtil.LEVEL_DAY:
for (int i=calendar.get(Calendar.DAY_OF_WEEK) - 1; i < 7; i++) {
list.add(new Entry<Integer,String>(GridPickerAdapter.TYPE_TITLE,TimeUtil.Day.getDayNameOfWeek(i)));
}
for (int i=0; i < calendar.get(Calendar.DAY_OF_WEEK) - 1; i++) {
list.add(new Entry<Integer,String>(GridPickerAdapter.TYPE_TITLE,TimeUtil.Day.getDayNameOfWeek(i)));
}
for (int i=0; i < calendar.getActualMaximum(Calendar.DATE); i++) {
list.add(new Entry<Integer,String>(GridPickerAdapter.TYPE_CONTNET_ENABLE,String.valueOf(i + 1)));
}
break;
default :
break;
}
if (configList == null || configList.size() < 3) {
configList=new ArrayList<GridPickerConfig>();
configList.add(new GridPickerConfig(TimeUtil.NAME_YEAR,"" + selectedItemList.get(0),selectedItemList.get(0) - 1 - minDateDetails[0],5,4));
configList.add(new GridPickerConfig(TimeUtil.NAME_MONTH,"" + selectedItemList.get(1),selectedItemList.get(1) - 1,4,3));
configList.add(new GridPickerConfig(TimeUtil.NAME_DAY,"" + selectedItemList.get(2),selectedItemList.get(2) - 1 + 7,7,6));
}
return list;
}
