/** 
 * Matches expression with the value.
 */
public boolean match(final int value){
  if (a == 0) {
    return value == b;
  }
  if (a > 0) {
    if (value < b) {
      return false;
    }
    return (value - b) % a == 0;
  }
  if (value > b) {
    return false;
  }
  return (b - value) % (-a) == 0;
}
