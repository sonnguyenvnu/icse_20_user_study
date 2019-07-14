/** 
 * ?????????????unit? <p>time???yyyy-MM-dd HH:mm:ss</p>
 * @param time ?????
 * @param unit <ul><li> {@link TimeUnit#MSEC}:??</li> <li> {@link TimeUnit#SEC }:?</li> <li> {@link TimeUnit#MIN }:?</li> <li> {@link TimeUnit#HOUR}:??</li> <li> {@link TimeUnit#DAY }:?</li> </ul>
 * @return unit???
 */
public static long getIntervalByNow(String time,TimeUnit unit){
  return getIntervalByNow(time,unit,DEFAULT_SDF);
}
