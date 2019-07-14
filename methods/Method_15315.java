/** 
 * ???????12:30,?????...
 * @param date
 * @return
 */
@SuppressLint("SimpleDateFormat") public static String getSmartDate(long date){
  int[] nowDetails=getWholeDetail(System.currentTimeMillis());
  int[] smartDetail=getWholeDetail(date);
  String smartDate="";
  if (nowDetails[0] == smartDetail[0]) {
    if (nowDetails[1] == smartDetail[1]) {
      String time=" " + StringUtil.getString(new SimpleDateFormat("HH:mm").format(date));
      long day=nowDetails[2] - smartDetail[2];
      if (day >= 3) {
        smartDate=String.valueOf(smartDetail[2]) + "?" + time;
      }
 else       if (day >= 2) {
        smartDate="??" + time;
      }
 else       if (day >= 1) {
        smartDate="??" + time;
      }
 else       if (day >= 0) {
        if (0 == (nowDetails[HOUR_OF_DAY] - smartDetail[HOUR_OF_DAY])) {
          long minute=nowDetails[MINUTE] - smartDetail[MINUTE];
          if (minute < 1) {
            smartDate="??";
          }
 else           if (minute < 31) {
            smartDate=minute + "???";
          }
 else {
            smartDate=time;
          }
        }
 else {
          smartDate=time;
        }
      }
 else       if (day >= -1) {
        smartDate="??" + time;
      }
 else       if (day >= -2) {
        smartDate="??" + time;
      }
 else {
        smartDate=String.valueOf(smartDetail[2]) + "?" + time;
      }
    }
 else {
      smartDate=String.valueOf(smartDetail[1]) + "?" + String.valueOf(smartDetail[2]) + "?";
    }
  }
 else {
    smartDate=String.valueOf(smartDetail[0]) + "?" + String.valueOf(smartDetail[1]) + "?";
  }
  return smartDate;
}
