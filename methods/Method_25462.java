/** 
 * Return the worst case edit distance between strings of this length 
 */
public static int getWorstCaseEditDistance(int sourceLength,int targetLength,int changeCost,int openGapCost,int continueGapCost){
  int maxLen=Math.max(sourceLength,targetLength);
  int minLen=Math.min(sourceLength,targetLength);
  int totChangeCost=scriptCost(openGapCost,continueGapCost,maxLen - minLen) + minLen * changeCost;
  int blowAwayCost=scriptCost(openGapCost,continueGapCost,sourceLength) + scriptCost(openGapCost,continueGapCost,targetLength);
  return Math.min(totChangeCost,blowAwayCost);
}
