/** 
 * ????
 * @param tabPosition
 * @param selectedItemList
 * @return
 */
private synchronized List<Entry<Integer,String>> getList(int tabPosition,ArrayList<Integer> selectedItemList){
  int level=TimeUtil.LEVEL_HOUR + tabPosition;
  if (selectedItemList == null || selectedItemList.size() < MIN_LENGHT || TimeUtil.isContainLevel(level) == false) {
    Log.e(TAG,"getList  (selectedItemList == null || selectedItemList.size() < MIN_LENGHT" + " || TimeUtil.isContainLevel(level) == false >> return null;");
    return null;
  }
  list=new ArrayList<Entry<Integer,String>>();
  boolean isContinuous=TimeUtil.fomerIsEqualOrBigger(maxTimeDetails,minTimeDetails);
  isCenter[0]=selectedItemList.get(0) != minTimeDetails[0] && selectedItemList.get(0) != maxTimeDetails[0];
  isEqualStart[0]=selectedItemList.get(0) == minTimeDetails[0];
  if (selectedItemList.size() >= 2) {
    isCenter[1]=selectedItemList.get(1) != minTimeDetails[1] && selectedItemList.get(1) != maxTimeDetails[1];
    isCenter[1]=isCenter[0] || isCenter[1];
    isEqualStart[1]=selectedItemList.get(1) == minTimeDetails[1];
  }
  boolean isEnable;
switch (level) {
case TimeUtil.LEVEL_HOUR:
    for (int i=0; i < 24; i++) {
      list.add(new Entry<Integer,String>(getItemType((isContinuous && (i >= minTimeDetails[0] && i <= maxTimeDetails[0])) || (isContinuous == false && (i >= minTimeDetails[0] || i <= maxTimeDetails[0]))),String.valueOf(i)));
    }
  break;
case TimeUtil.LEVEL_MINUTE:
for (int i=0; i < 60; i++) {
  if (minTimeDetails[0] == maxTimeDetails[0]) {
    isEnable=i >= minTimeDetails[1] && i <= maxTimeDetails[1];
  }
 else {
    isEnable=isCenter[0] || (isEqualStart[0] && i >= minTimeDetails[1]) || (selectedItemList.get(0) == maxTimeDetails[0] && i <= maxTimeDetails[1]);
  }
  list.add(new Entry<Integer,String>(getItemType(isEnable),String.valueOf(i)));
}
break;
case TimeUtil.LEVEL_SECOND:
for (int i=0; i < 60; i++) {
if (minTimeDetails[0] == maxTimeDetails[0] && minTimeDetails[1] == maxTimeDetails[1] && i == minTimeDetails[1]) {
if (isContinuous) {
  isEnable=i >= minTimeDetails[1] && i <= maxTimeDetails[1];
}
 else {
  isEnable=i >= minTimeDetails[1] || i <= maxTimeDetails[1];
}
}
 else {
isEnable=isCenter[0] || (isEqualStart[0] && i >= minTimeDetails[1]) || (selectedItemList.get(0) == maxTimeDetails[0] && i <= maxTimeDetails[1]);
}
list.add(new Entry<Integer,String>(getItemType(isEnable),String.valueOf(i)));
}
break;
default :
break;
}
return list;
}
