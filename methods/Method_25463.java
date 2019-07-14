/** 
 * Return the cost of a script consisting of a contiguous sequence of insertions or a contiguous sequence of deletions.
 */
private static int scriptCost(int openGapCost,int continueGapCost,int scriptLength){
  return (scriptLength == 0) ? 0 : openGapCost + scriptLength * continueGapCost;
}
