/** 
 * Value equals.
 * @param leftValue the left value
 * @param rightValue the right value
 * @return the boolean
 */
private boolean valueEquals(V leftValue,V rightValue){
  if (leftValue == rightValue) {
    return true;
  }
  if (leftValue == null || rightValue == null) {
    return false;
  }
  return leftValue.equals(rightValue);
}
