/** 
 * Obtains an instance of <code>Days</code> that may be cached. <code>Days</code> is immutable, so instances can be cached and shared. This factory method provides access to shared instances.
 * @param days  the number of days to obtain an instance for
 * @return the instance of Days
 */
public static Days days(int days){
switch (days) {
case 0:
    return ZERO;
case 1:
  return ONE;
case 2:
return TWO;
case 3:
return THREE;
case 4:
return FOUR;
case 5:
return FIVE;
case 6:
return SIX;
case 7:
return SEVEN;
case Integer.MAX_VALUE:
return MAX_VALUE;
case Integer.MIN_VALUE:
return MIN_VALUE;
default :
return new Days(days);
}
}
