/** 
 * Returns the square root of  {@code x}, rounded with the specified rounding mode.
 * @throws IllegalArgumentException if {@code x < 0}
 * @throws ArithmeticException if {@code mode} is {@link RoundingMode#UNNECESSARY} and {@code sqrt(x)} is not an integer
 */
@GwtIncompatible @SuppressWarnings("fallthrough") public static int sqrt(int x,RoundingMode mode){
  checkNonNegative("x",x);
  int sqrtFloor=sqrtFloor(x);
switch (mode) {
case UNNECESSARY:
    checkRoundingUnnecessary(sqrtFloor * sqrtFloor == x);
case FLOOR:
case DOWN:
  return sqrtFloor;
case CEILING:
case UP:
return sqrtFloor + lessThanBranchFree(sqrtFloor * sqrtFloor,x);
case HALF_DOWN:
case HALF_UP:
case HALF_EVEN:
int halfSquare=sqrtFloor * sqrtFloor + sqrtFloor;
return sqrtFloor + lessThanBranchFree(halfSquare,x);
default :
throw new AssertionError();
}
}
