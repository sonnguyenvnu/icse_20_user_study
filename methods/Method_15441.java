/** 
 * ??????? TODO ??? ??????
 * @param time
 * @return
 */
public static long getTimeMillis(String time){
  return StringUtil.isEmpty(time,true) ? 0 : getTimeStamp(time).getTime();
}
