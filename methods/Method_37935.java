/** 
 * Iterates to next value at the beginning of the loop.
 */
public boolean next(){
  if (!looping) {
    return false;
  }
  if (last) {
    return false;
  }
  if (count == 0) {
    value=start;
    first=true;
  }
 else {
    value+=step;
    first=false;
  }
  count++;
  last=isLastIteration(value + step);
  return true;
}
