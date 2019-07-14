/** 
 * ??????????????prevStr?currStr???????<br> Determines the start index of the substring in the String most recently added to the MDAG that corresponds to the _transition path that will be next up for minimization processing. <p/> The "minimization processing start index" is defined as the index in  {@code prevStr} which starts the substringcorresponding to the _transition path that doesn't have its right language extended by  {@code currStr}. The _transition path of the substring before this point is not considered for minimization in order to limit the amount of times the equivalence classes of its nodes will need to be reassigned during the processing of Strings which share prefixes.
 * @param prevStr the String most recently added to the MDAG
 * @param currStr the String next to be added to the MDAG
 * @return an int of the index in {@code prevStr} that starts the substring correspondingto the _transition path next up for minimization processing
 */
private int calculateMinimizationProcessingStartIndex(String prevStr,String currStr){
  int mpsIndex;
  if (!currStr.startsWith(prevStr)) {
    int shortestStringLength=Math.min(prevStr.length(),currStr.length());
    for (mpsIndex=0; mpsIndex < shortestStringLength && prevStr.charAt(mpsIndex) == currStr.charAt(mpsIndex); mpsIndex++) {
    }
    ;
  }
 else   mpsIndex=-1;
  return mpsIndex;
}
