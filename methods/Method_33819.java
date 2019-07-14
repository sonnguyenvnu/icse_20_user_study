/** 
 * ????????????? ??? ---- ??12:30????????????? ---- ??12:30: ??????????????????????? ?? ---- ?????? - ??? ---- ?????? - ????
 */
public void loadData(){
  String oneData=SPUtils.getString("everyday_data","2016-11-26");
  if (!oneData.equals(TimeUtil.getData())) {
    isShowLoading.setValue(true);
    if (TimeUtil.isRightTime()) {
      isOldDayRequest=false;
      mEverydayModel.setData(getTodayTime().get(0),getTodayTime().get(1),getTodayTime().get(2));
      showBannerPage();
      showRecyclerViewData();
    }
 else {
      ArrayList<String> lastTime=TimeUtil.getLastTime(getTodayTime().get(0),getTodayTime().get(1),getTodayTime().get(2));
      mEverydayModel.setData(lastTime.get(0),lastTime.get(1),lastTime.get(2));
      year=lastTime.get(0);
      month=lastTime.get(1);
      day=lastTime.get(2);
      isOldDayRequest=true;
      handleCache();
    }
  }
 else {
    isOldDayRequest=false;
    if (!isHaveData) {
      handleCache();
    }
  }
}
