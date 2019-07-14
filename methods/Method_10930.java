/** 
 * ?????????????unit? <p>time?Date??</p>
 * @param time Date????
 * @param unit <ul><li> {@link TimeUnit#MSEC}: ??</li> <li> {@link TimeUnit#SEC }: ?</li> <li> {@link TimeUnit#MIN }: ?</li> <li> {@link TimeUnit#HOUR}: ??</li> <li> {@link TimeUnit#DAY }: ?</li> </ul>
 * @return unit???
 */
public static long getIntervalByNow(Date time,TimeUnit unit){
  return getIntervalTime(getCurTimeDate(),time,unit);
}
