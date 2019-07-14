/** 
 * ?????????????unit?
 * @param milliseconds ?????
 * @param unit         <ul><li> {@link TimeUnit#MSEC}: ??</li> <li> {@link TimeUnit#SEC }: ?</li> <li> {@link TimeUnit#MIN }: ?</li> <li> {@link TimeUnit#HOUR}: ??</li> <li> {@link TimeUnit#DAY }: ?</li> </ul>
 * @return unit???
 */
private static long milliseconds2Unit(long milliseconds,TimeUnit unit){
switch (unit) {
case MSEC:
    return milliseconds / MSEC;
case SEC:
  return milliseconds / SEC;
case MIN:
return milliseconds / MIN;
case HOUR:
return milliseconds / HOUR;
case DAY:
return milliseconds / DAY;
default :
break;
}
return -1;
}
