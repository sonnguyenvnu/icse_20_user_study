/** 
 * Obtains an instance of <code>Minutes</code> that may be cached. <code>Minutes</code> is immutable, so instances can be cached and shared. This factory method provides access to shared instances.
 * @param minutes  the number of minutes to obtain an instance for
 * @return the instance of Minutes
 */
public static Minutes minutes(int minutes){
switch (minutes) {
case 0:
    return ZERO;
case 1:
  return ONE;
case 2:
return TWO;
case 3:
return THREE;
case Integer.MAX_VALUE:
return MAX_VALUE;
case Integer.MIN_VALUE:
return MIN_VALUE;
default :
return new Minutes(minutes);
}
}
