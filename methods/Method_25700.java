/** 
 * Set the cost of all the alternatives for this formal parameter to be Inf. 
 */
void invalidateAllAlternatives(Parameter formal){
  for (int actualIndex=0; actualIndex < costMatrix[formal.index()].length; actualIndex++) {
    if (actualIndex != formal.index()) {
      costMatrix[formal.index()][actualIndex]=Double.POSITIVE_INFINITY;
    }
  }
}
