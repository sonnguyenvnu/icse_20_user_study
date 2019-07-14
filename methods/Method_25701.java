/** 
 * Update the cost of the given pairing. 
 */
void updatePair(ParameterPair p,double cost){
  costMatrix[p.formal().index()][p.actual().index()]=cost;
}
