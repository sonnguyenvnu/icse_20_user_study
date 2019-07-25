/** 
 * The distance between the two locations, where the distance from the end of  one line to the beginning of the next is considered to be 9999 times as  great as the distance between adjacent characters.
 * @param locA
 * @param locB
 * @return
 */
private static int Dist(PCalLocation locA,PCalLocation locB){
  return 9999 * Math.abs(locA.getLine() - locB.getLine()) + Math.abs(locA.getColumn() - locB.getColumn());
}
