/** 
 * ???
 * @return
 */
public static boolean noDisturb(){
  return getBoolean(KEY_NO_DISTURB,noDisturb) && TimeUtil.isNowInTimeArea(NO_DISTURB_START_TIME,NO_DISTURB_END_TIME);
}
