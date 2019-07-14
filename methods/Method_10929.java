/** 
 * ?????????????unit? <p>time???format</p>
 * @param time   ?????
 * @param unit   <ul><li> {@link TimeUnit#MSEC}: ??</li> <li> {@link TimeUnit#SEC }: ?</li> <li> {@link TimeUnit#MIN }: ?</li> <li> {@link TimeUnit#HOUR}: ??</li> <li> {@link TimeUnit#DAY }: ?</li> </ul>
 * @param format ????
 * @return unit???
 */
public static long getIntervalByNow(String time,TimeUnit unit,SimpleDateFormat format){
  return getIntervalTime(getCurTimeString(),time,unit,format);
}
