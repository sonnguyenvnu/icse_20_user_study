/** 
 * @return true if detect moves should be enabled when performing the Diff. Detect moves isenabled by default
 */
private static boolean isDetectMovesEnabled(@Nullable Diff<Boolean> detectMoves){
  return detectMoves == null || detectMoves.getNext() == null || detectMoves.getNext();
}
