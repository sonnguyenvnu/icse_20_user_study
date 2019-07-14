/** 
 * ????
 */
private void saveDate(){
  isHaveData=true;
  isShowLoading.setValue(false);
  if (isOldDayRequest) {
    ArrayList<String> lastTime=TimeUtil.getLastTime(getTodayTime().get(0),getTodayTime().get(1),getTodayTime().get(2));
    SPUtils.putString("everyday_data",lastTime.get(0) + "-" + lastTime.get(1) + "-" + lastTime.get(2));
  }
 else {
    SPUtils.putString("everyday_data",TimeUtil.getData());
  }
}
