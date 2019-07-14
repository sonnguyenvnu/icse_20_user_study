/** 
 * ?YYYY/MM/DD HH24:MI:SS??????????
 * @return ??????
 * @since 1.0
 * @history
 */
public static String getSysDateTimeString(){
  return toString(new Date(System.currentTimeMillis()),DateUtils.sdfDateTime);
}
