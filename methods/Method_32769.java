/** 
 * Obtains an instance of <code>Years</code> that may be cached. <code>Years</code> is immutable, so instances can be cached and shared. This factory method provides access to shared instances.
 * @param years  the number of years to obtain an instance for
 * @return the instance of Years
 */
public static Years years(int years){
switch (years) {
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
return new Years(years);
}
}
