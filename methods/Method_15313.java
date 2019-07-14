/** 
 * ?long?????????????????? ???1970?1?1?8?????
 * @param duration
 * @return
 */
public static String getSmartTime(long duration){
  int[] smartDetail=getWholeDetail(duration);
  String smartTime="";
  if (smartDetail[5] > 0) {
    smartTime=String.valueOf(smartDetail[5]) + "?" + smartTime;
  }
  if (smartDetail[4] > 0) {
    smartTime=String.valueOf(smartDetail[4]) + "?" + smartTime;
  }
  if (smartDetail[3] > 8) {
    smartTime=String.valueOf(smartDetail[3]) + "?" + String.valueOf(smartDetail[4]) + "?";
  }
  if (smartDetail[2] > 1) {
    smartTime=String.valueOf(smartDetail[2]) + "?" + String.valueOf(smartDetail[3]) + "?";
  }
  if (smartDetail[1] > 1) {
    smartTime=String.valueOf(smartDetail[1]) + "?" + String.valueOf(smartDetail[2]) + "?";
  }
  if (smartDetail[0] > 1970) {
    smartTime=String.valueOf(smartDetail[0]) + "?" + smartTime;
  }
  return smartTime;
}
