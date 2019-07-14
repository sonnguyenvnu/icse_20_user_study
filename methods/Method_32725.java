/** 
 * Obtains an instance of <code>Weeks</code> that may be cached. <code>Weeks</code> is immutable, so instances can be cached and shared. This factory method provides access to shared instances.
 * @param weeks  the number of weeks to obtain an instance for
 * @return the instance of Weeks
 */
public static Weeks weeks(int weeks){
switch (weeks) {
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
return new Weeks(weeks);
}
}
